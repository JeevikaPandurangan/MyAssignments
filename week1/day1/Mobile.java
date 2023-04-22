package week1.day1;

public class Mobile {
	
	protected void makeCall(String mobileModel,float mobileWeight) {
		System.out.println(mobileModel);
		System.out.println(mobileWeight);
		System.out.println("Make a call....");

	}
	public void sendMsg(boolean isFullCharged, int mobileCost) {
		System.out.println(isFullCharged);
		System.out.println(mobileCost);
		System.out.println("Message send...");

	}
	public static void main(String[] args) {
		//Creating Object for this class
		Mobile mob = new Mobile();
		mob.makeCall("Redmi", 25.5f);
		mob.sendMsg(true, 25000);
	    System.out.println("This is my Mobile");
	}
}
