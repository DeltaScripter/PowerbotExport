package deltashearer;

public class ShearData {

	
	public enum choco{
		
		CHOCOLATEBAR(1973), CHOCOLATEPOWDER(1975);
		
		int id;
		choco(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	
}
