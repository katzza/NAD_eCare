package ecare;

import ecare.model.Contract;
import ecare.model.Option;
import ecare.model.Tariff;
import ecare.service.ContractService;
import ecare.service.OptionService;
import ecare.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    TariffService tariffService;

    @Autowired
    OptionService optionService;

    @Autowired
    ContractService contractService;

    @Override
    public void run(String... args) throws Exception {

        Option multiSIM = new Option("MultiSIM", 5.5);
        Option hotspot = new Option("Hotspot Flat", 9.0);
        Option spotify = new Option("Spotify", 5.5);

        optionService.saveOption(multiSIM);
        optionService.saveOption(hotspot);
        optionService.saveOption(spotify);

        Tariff tariffS = tariffService.saveTariff(new Tariff("Tariff S: 40 min 8Gb", 7.0));
        Tariff tariffM = tariffService.saveTariff(new Tariff("Tariff M: 128 min 20Gb", 10.0));
        Tariff tariffL = tariffService.saveTariff(new Tariff("Tariff L: 500 min 50 Gb", 15.0));

        tariffL.getOptions().add(spotify);
        tariffL.getOptions().add(hotspot);
        spotify.getTariffs().add(tariffL);
        hotspot.getTariffs().add(tariffL);
        tariffService.saveTariff(tariffL);

        Contract contract = new Contract(tariffS, "3127670");
        contractService.saveContract(contract);

        System.out.println(tariffService.getAllTariffs().stream());
    }

}


