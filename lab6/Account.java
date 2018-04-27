// Example of a bank-account class
// Stubs-only version;  example of javadoc for generating documentation.  
// R. Brown, 1/00

/** A class for representing bank accounts.
    @author R. Brown, 1/00 */
    
public class Account {

  // class variables

  /** Lowest unused account number, for generating unique identifying 
      numbers for instances of <code>Account</code>. */
  static long nextAccountNumber = 1;
  
  // state variables (one per instance of the class)

  /** Current balance in this instance of <code>Account</code>. */
  double balance;

  /** Original balance when this instance of <code>Account</code> 
      was constructed. */ 
  double initialBalance;

  /** Unique identifying number for an instance of <code>Account</code>. */
  long accountNumber;

  /** Name of the owner of an instance of <code>Account</code>. */
  String owner;  

  // constructors

  /** Initializes an instance of <code>Account</code>, with its own account number.
      @param name The name of the owner
      @param init_bal  The initial balance   */
  public Account(String name, double init_bal) {
    owner = name;
    balance = init_bal;
    initialBalance = init_bal;
    accountNumber = nextAccountNumber++;
  }

  // methods

  /** Retrieve the current balance.
      @return The value of the state variable <code>balance</code> */
  public double getBalance() { return balance; }

  /** Retrieve the original balance.
      @return The value of the state variable <code>initialBalance</code> */
  public double getInitialBalance() { return initialBalance; }

  /** Retrieve the account number.
      @return The value of the state variable <code>accountNumber</code> */
  public long getAccountNumber() { return accountNumber; }

  /** Retrieve the owner.
      @return The value of the state variable <code>owner</code> */
  public String getOwner() { return owner; }

  /** Add an amount to the balance.
      @param amount  Non-negative amount to add 
      @return The resulting <code>balance</code> after addition 
      @exception IllegalArgumentException  
	Thrown if <code>amount</code> is negative */
  public double deposit(double amount) { return 0.0; }

  /** Remove an amount from the balance.
      @param amount  Non-negative amount to subtract
      @return The resulting <code>balance</code> after subtraction 
      @exception IllegalArgumentException  
	Thrown if <code>amount</code> is negative */
  public double withdraw(double amount) { return 0.0; }

  /** Returns a string representation of this object. 
      @return A string showing the owner, account number, balance and 
      initial balance for this instance of <code>Account</code>. */ 
  public String toString() { return ""; }
}
