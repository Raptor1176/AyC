
public class PDinamica {
	
	public static int[] parcial;
	public static int rec;
	public static int sum;
	public static int catalan(int n){
		if (n==0)
			return 1;
		else{
			int c=0;
			for(int i=0; i<=n-1;i++){/*
				if (parcial[i]==0){
					parcial[i]=catalan(i);
					rec++;
				}
				if (parcial[n-i-1]==0){
					parcial[n-i-1]=catalan(n-i-1);
					rec++;
					}
				c+=parcial[i]*parcial[n-i-1];*/
				c+=catalan(i)*catalan(n-1-i);
				rec+=2;
				sum++;
			}
			return c;				
			}
	};
	
	/*
	public static int fibb(int n){
		if ((n==0)||(n==1))
			return 1;
		else					
			return fibb(n-1)+fibb(n-2);
	}
	*/
	
	public static int[] sapoP;	
	public static int sapo(int o, int d){
		if (o==d)
			return 1;
		else 
			if (o>d)
				return 0;
			else{
				int r=0;				
				for (int i=1; i<=(d-o); i++){
					if (sapoP[o+i]==0)
						sapoP[o+i]=sapo(o+i,d);
					r+=sapoP[o+i];
					//r+=sapo(o+i,d);
				}
				return r;
			}						
		
			
	}
	
	public static void main(String[] args) {
		
		int N=2;		
		parcial= new int[N];
		for(int i=1; i<N;i++)
			parcial[i]=0;	
		parcial[0]=1;		
		rec=0;		
		sum=0;
		System.out.println("N=" +N);
		System.out.println("catalan=" +catalan(N));
		System.out.println("rec=" +rec);	
		System.out.println("sum=" +sum);
		for(int i=0; i<N;i++)
			System.out.print(parcial[i]+" ");		
		

		
		/*
		int N=30;
		sapoP= new int[N+1];
		for(int i=0; i< sapoP.length; i++)
			sapoP[i]=0;
		System.out.println("sapo combea "+sapo(0,N));
		for(int i=0; i< sapoP.length; i++)
			System.out.println(sapoP[i]);
			*/
	}

}
