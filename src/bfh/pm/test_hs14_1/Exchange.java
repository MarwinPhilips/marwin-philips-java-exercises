package bfh.pm.test_hs14_1;

import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

/**
 * The exchange model.
 *
 */
public class Exchange extends Observable implements Runnable {
	
	
	private HashMap<String, Double> rates;
	private String currency = "EUR";
	private Thread thread; 
	
	/**
	 * Constructor. Creates a new exchange-model with some initial data. 
	 */
	public Exchange() {
		this.rates = new HashMap<>();
		this.rates.put("EUR", 1.2023);
		this.rates.put("USD", 0.9877);
		this.rates.put("GBP", 1.5315);
		this.rates.put("JPY", 0.0082);
	}
	
	/**
	 * Gets all supported currencies.
	 * 
	 * @return The currencies as a set of Strings. 
	 */
	public Set<String> getCurrencies() {
		return this.rates.keySet();
	}
	
	/**
	 * Adds a new currency to the model.
	 * 
	 * @param currency A label representing the currency.
	 * @param initialRate The initial rate to CHF of the currency.
	 */
	public void addCurrency(String currency, double initialRate) {
		this.rates.put(currency, initialRate);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the current currency.
	 * 
	 * @param currency The currency.
	 */
	public void setCurrency(String currency) {
		if ( !rates.containsKey(currency) ) {
			throw new RuntimeException("Unsupported currency '" + currency + "'");
		}
		this.currency = currency;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Gets the current currency.
	 *
	 * @return The currenct currency.
	 */
	public String getCurrency() {
		return this.currency;
	}
	
	/**
	 * Gets the actual rate of the current currency.
	 * 
	 * @return The rate as formatted string.
	 */
	public String getRate() {
		return String.format("%.4f", this.rates.get(this.getCurrency()));
	}
	
	/**
	 * Returns whether the exchange is updating the actual rate of the current currency.
	 * 
	 * @return True if exchange is in updating mode otherwise false.
	 */
	public boolean isRunning() {
		return this.thread != null;
	}
	
	/**
	 * Starts updating the rate of the current currency.
	 */
	public void start() {
		if (this.thread == null) {
			this.thread = new Thread(this);
			this.thread.setDaemon(true);
			this.thread.start();
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Stops updating the rate of the current currency.
	 */
	public void stop() {
		if (this.thread != null) {
			this.thread = null;
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	@Override
	public final void run() {
		while (this.thread != null) {
			try {
				Thread.sleep((int)(Math.random() * 2000 + 1000));
			} catch (InterruptedException e) {
				// do nothing
			}
			if (this.thread != null) {
				double v = this.rates.get(this.currency) + (Math.random() - 0.5) / 500;
				this.rates.put(this.currency, v);
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
}
