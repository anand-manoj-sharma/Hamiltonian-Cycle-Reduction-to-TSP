import java.util.*;

public class DAAFINAL {

    static int x[],k,n,inc=1,min=Integer.MAX_VALUE;
    static int wt[][];
    static int store[],flag=0;
    static boolean check=false;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no. of Vertices:-");
        n=sc.nextInt();
        int p[][]=new int[n][n];
        x=new int[n];
        store=new int[n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                p[i][j]=sc.nextInt();
            }
        }

        System.out.println("****ALL POSSIBLE HAMILTONIAN CYCLES****\n");

        x[0]=1;
        k=1;

            TSPREDUCTION(p);
        System.out.println("");
        System.out.println("TSP RESULT--> ");
            for(int i=0;i<n;i++)
           {
            System.out.print(store[i]+" ");
           }
        System.out.println("");
        System.out.print("........................");

    }

    public static void Hamiltonian(int k,int p[][])
    {
        do{
            NextVertex(k,p);
            if(x[k]==0)
            {
                return;
            }
            if(k==n-1)
            {
                int sum=0;
                System.out.println("");
                System.out.print("("+inc++ +")  ");
                flag=1;
                for(int i=0;i<n;i++)
                {
                    if(i<n-1)
                    {
                        sum=sum+wt[x[i]-1][x[i+1]-1];
                    }
                    else
                    {
                        sum=sum+wt[x[i]-1][x[0]-1];
                    }
                    try{
                        Thread.sleep(1000);
                        System.out.print(x[i]+" ");
                    }
                    catch(Exception e){}
                }
                 System.out.println(" --->cost="+sum);
                if(sum==0)
                {
                    for(int i=0;i<n;i++)
                    {
                        store[i]=x[i];
                    }
                    check = true;
                }
            }
            else
            {
                Hamiltonian(k+1,p);
                if(check==true){
                    return;
                }
            }
        }while(true);
    }
    public static void NextVertex(int k,int p[][])
    {
        do{
            int j;
            x[k]=(x[k]+1)%(n+1);
            if(x[k]==0)
            {
                return;
            }

            if(p[x[k-1]-1][x[k]-1]!=0)
            {
                for(j=0;j<k;j++)
                {
                    if(x[j]==x[k])
                    {
                        break;
                    }
                }

                if(j==k)
                {
                    if(k<n-1)
                    {
                        return;
                    }
                    else if(k==n-1 && p[x[k]-1][x[0]-1]!=0)
                    {
                        return;
                    }
                }
            }
        }while(true);

    }

    public static void TSPREDUCTION(int arr[][])
    {
         wt=new int[arr.length][arr.length];

        ////////Weight matrix creation////////

        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(arr[i][j]==1)
                {
                    wt[i][j]=0;
                }
                else
                {
                    wt[i][j]=1;
                }
            }
        }


        ///////Complete matrix creation///////

        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr.length;j++)
            {
                if(arr[i][j]==0 && i!=j)
                {
                    arr[i][j]=1;
                }
            }
        }

        Hamiltonian(1,arr);

    }
}
