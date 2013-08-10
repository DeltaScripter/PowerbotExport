package essenceminer;

public class EssenceData {

	
	public enum essence{
		
		ESSENCE(1436), ESSENCEROCK(2491),AUBURYID(5913),PORTAL(39831);
		
		int id;
		essence(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	
}
