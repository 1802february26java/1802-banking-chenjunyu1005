import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import com.revature.exception.IlleaglTransactionException;

import com.revature.service.BankService;

public class BankServiceTest {

	private static final BankService bankService =new BankService();
	private static Logger logger = Logger.getLogger(BankServiceTest.class);
	private static double initialAmount=1000;


	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	

	@Test
	public void viewInitialBalanceTest(){
		
		logger.trace("Checking if the initial amount equals to 1000");
		assertThat(bankService.viewBalance("Paul", "George"),equalTo(initialAmount));
		
	}
	
	@Test
	public void depositeExceptionTest() throws IlleaglTransactionException{
		logger.trace("Making sure the deposit amount is a positive number");
		expectedException.expect(IlleaglTransactionException.class);
		bankService.depositMoney("Junyu", "Chen",-100 );
	
	
		
	}
	
	
}
