package th.ac.ku.atm.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class BankAccountService {

    private List<BankAccount> bankAccountList;
    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BankAccount> getCustomerBankAccount(int customerId) {

        String url = "http://bankaccount:8081/api/bankaccount/customer/" + customerId;
        ResponseEntity<BankAccount[]> response = restTemplate.getForEntity(url, BankAccount[].class);
        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);

    }

    @PostConstruct
    public void postConstruct() { this.bankAccountList = new ArrayList<>();
    }

    public void openAccount(BankAccount bankAccount) {
        String url = "http://bankaccount:8081/api/bankaccount";

        restTemplate.postForObject(url, bankAccount, BankAccount.class);
    }

    public List<BankAccount> getBankAccounts() {
        String url = "http://bankaccount:8081/api/bankaccount/";

        ResponseEntity<BankAccount[]> response = restTemplate.getForEntity(url, BankAccount[].class);
        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);
    }

    public BankAccount getBankAccount(int id) {
        String url = "http://bankaccount:8081/api/bankaccount/" + id;

        ResponseEntity<BankAccount> response = restTemplate.getForEntity(url, BankAccount.class);
        return response.getBody();
    }

    public void editBankAccount(BankAccount bankAccount, double cash, String action) {
        String url = "http://bankaccount:8081/api/bankaccount/edit/" + bankAccount.getId();
        TreeMap<String, Double> transaction = new TreeMap<>();
        transaction.put(action,cash);
        restTemplate.put(url, transaction);
    }

    public void deleteBankAccount(BankAccount bankAccount){
        String url = "http://bankaccount:8081/api/bankaccount/" + bankAccount.getId();
        restTemplate.delete(url, bankAccount);
    }


}
