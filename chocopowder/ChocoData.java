package chocopowder;

public class ChocoData {

	
	public enum choco{
		
		CHOCOLATEBAR(1973), CHOCOLATEPOWDER(1975),PIEDISH(2313);
		
		int id;
		choco(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	
}
