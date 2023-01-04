package ecare;

import ecare.model.*;
import ecare.repository.RoleRepository;
import ecare.service.ContractService;
import ecare.service.OptionService;
import ecare.service.TariffService;
import ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class InitData implements CommandLineRunner {

    @Autowired
    TariffService tariffService;

    @Autowired
    OptionService optionService;

    @Autowired
    ContractService contractService;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleUser.setStatus(Status.ACTIVE);
        roleRepository.save(roleUser);

        User user = new User();
        user.setStatus(Status.ACTIVE);
        user.getRoles().add(roleUser);
        user.setEmail("test@aa.aa");
        user.setUsername("test@aa.aa");
        user.setPassword("test");
        userService.register(user);



        Option multiSIM = new Option("MultiSIM", "MultiSIM", 5.5);
        Option hotspot = new Option("HotspotFlat", "Hotspot Flat", 9.0);
        Option spotify = new Option("Spotify", "Spotify", 6.5);
        Option outOfReach = new Option("outOfReach", "without Tariff", 6.5);

        optionService.saveOption(multiSIM);
        optionService.saveOption(hotspot);
        optionService.saveOption(spotify);
        optionService.saveOption(outOfReach);

        Tariff tariffS = tariffService.saveTariff(new Tariff("S", "Tariff S: 40 min 8Gb", 7.0, 1));
        Tariff tariffM = tariffService.saveTariff(new Tariff("M", "Tariff M: 128 min 20Gb", 10.0, 2));
        Tariff tariffL = tariffService.saveTariff(new Tariff("L", "Tariff L: 500 min 50 Gb", 15.0, 3));
        Tariff tariffXL = tariffService.saveTariff(new Tariff("XL", "Tariff XL: Unlimited", 20.0, 4));

        tariffL.getOptions().add(spotify);
        tariffService.saveTariff(tariffL);

        tariffL.getOptions().add(hotspot);
        tariffService.saveTariff(tariffL);

        tariffL.getOptions().add(multiSIM);
        tariffService.saveTariff(tariffL);

        tariffM.getOptions().add(spotify);
        tariffService.saveTariff(tariffM);

        Contract contract = new Contract(tariffL, "3127670");
        contractService.saveContract(contract);

    }

}


