package com.zky.springddd.snackmachine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zky.springddd.sharedkernel.Money;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/snackmachines")
@RequiredArgsConstructor
public class SnackMachineController {
    private final SnackMachineRepository snackMachineRepository;

    @GetMapping("{id}")
    @ResponseBody
    public SnackMachineDto getSnackMachine(@PathVariable long id) {
        return snackMachineRepository.findById(id).orElse(null);
    }
    
    @PutMapping("{id}/moneyInTransaction/{coinOrNote}")
    public void insertCoinOrNote(@PathVariable long id, @PathVariable String coinOrNote) {
        SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();

        if (coinOrNote.equalsIgnoreCase("Cent")) {
            snackMachine.insertMoney(Money.Cent);
        } else if (coinOrNote.equalsIgnoreCase("TenCent")) {
            snackMachine.insertMoney(Money.TenCent);
        } else if (coinOrNote.equalsIgnoreCase("Quarter")) {
            snackMachine.insertMoney(Money.Quarter);
        } else if (coinOrNote.equalsIgnoreCase("Dollar")) {
            snackMachine.insertMoney(Money.Dollar);
        } else if (coinOrNote.equalsIgnoreCase("FiveDollar")) {
            snackMachine.insertMoney(Money.FiveDollar);
        } else if (coinOrNote.equalsIgnoreCase("TwentyDollar")) {
            snackMachine.insertMoney(Money.TwentyDollar);
        }

        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
    }

    @PutMapping("{id}/moneyInTransaction")
    public void returnMoney(@PathVariable long id) {
        SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        snackMachine.returnMoney();
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
    }

    @PutMapping("{id}/{slotNumber}")
    public void buySnack(@PathVariable long id, @PathVariable int slotNumber) {
        SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
        SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
        snackMachine.buySnack(slotNumber);
        snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
    }

    public Money getWholMoney(SnackMachine snackMachine) {
        return snackMachine.getMoneyInside();
    }
}
