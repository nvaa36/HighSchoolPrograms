package scale;

import adapter.*;

// edit Option price or get the option price while always changing the price
// before getting the new one.
public class EditOptions extends ProxyAuto{
	private String modelname;
	private boolean get;
	
	public EditOptions(String model){
		modelname = model;
		get = false;
	}
	// sets the price of the automobile to 10 more than the current,
	// if the current price has already been printed
	public synchronized void setPrice(){
		if(get) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		a1.get(modelname).setBasePrice(a1.get(modelname).getBasePrice() + 10);
		/*try {
			Thread.currentThread().sleep(3000);
		} catch(InterruptedException e) {
			System.out.println("Interrupted!");
		}*/
		get = true;
		notifyAll();
	}
	// gets the new price and prints it if the price has been updated
	public synchronized void getPrice(){
		if(!get) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(a1.get(modelname).getTotalPrice());
		get = false;
		notifyAll();
	}
	// starts the cycle of getting and setting by creating instances of the Getter and Setter classes
	public void messupPrices(){
		new Getter(this);
		new Setter(this);
	}
}

class Getter implements Runnable {
	
	EditOptions get;
	
	public Getter(EditOptions g){
		get = g;
		new Thread(this).start();
	}
	public void run(){
		for(int i = 0; i < 10; i++){
			get.getPrice();
		}
	}
}

class Setter implements Runnable {
	
	EditOptions set;
	
	public Setter(EditOptions s){
		set = s;
		new Thread(this).start();
	}
	public void run(){
		for(int i = 0; i < 10; i++){
			set.setPrice();
		}
	}
}
