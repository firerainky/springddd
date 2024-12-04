package com.zky.springddd.snackmachine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/snackmachines")
public class SnackMachineController {
    static SnackMachine snackMachine = new SnackMachine();

    @GetMapping("{id}")
    public SnackMachineDto getSnackMachine(@PathVariable long id) {
        return snackMachine.convertToDto();
    }
    
    @PutMapping("{id}/moneyInTransaction/{coinOrNote}")
    public void insertMoney(@PathVariable long id, @PathVariable String coinOrNote) {
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
    }

    @PutMapping("{id}/moneyInTransaction")
    public void returnMoney(@PathVariable long id) {
        snackMachine.returnMoney();
    }

    @PutMapping("{id}/{slotNumber}")
    public void buySnack(@PathVariable long id, @PathVariable int slotNumber) {
        snackMachine.buySnack();
    }
}
