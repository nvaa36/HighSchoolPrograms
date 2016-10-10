import java.util.*;
import java.io.*;

public class Partitions
{
	private int n, index, maxSize;
	private ArrayList<Integer> list;
	private Integer num;
	
	public Partitions (String str)
	{
		num = Integer.parseInt(str);
		int temp = (int)num;
		while(temp < 1 || temp > 30)
		{
			System.out.print("Please enter an integer from 1-30 : ");
			Scanner kb = new Scanner(System.in);
			temp = (int)kb.nextInt();
		}
		list = new ArrayList<Integer>(temp);
		n = temp;
	}
	
	public Partitions ()
	{
		int temp = 0;
		do
		{
			System.out.print("Please enter an integer from 1-30 : ");
			Scanner kb = new Scanner(System.in);
			temp = (int)kb.nextInt();
		}while(temp < 1 || temp > 30);
		list = new ArrayList<Integer>(temp);
		n = temp;
	}
	
	public static void main(String[] args) throws IOException
	{
		Partitions part;
		if (args.length > 0)
		{
			String str = args[0];
			part = new Partitions(str);
		}
		else
			part = new Partitions();
		part.run();
	}
	
	public void run ()
	{
		for(maxSize = n;maxSize >= 1;maxSize--)
		{
			index = maxSize - 1;
			list.clear();
			for(int i = 0; i < maxSize; i++)
				list.add(1);
			displayPartitions(n, list);
		}
		System.out.print("\n" + n);
	}
	
	public void displayPartitions (int n, ArrayList<Integer> list)
	{
		if(index > 0)
		{
			int sum = 0;
			for(int temp : list)
				sum += temp;
			if(sum == n)
			{
				int temp = 0;
				boolean inOrder = true;
				for(int num : list)
				{
					if(num < temp)
						inOrder = false;
					temp = num;
				}
				if(inOrder)
				{
					for(int i = 0; i < list.size(); i++)
					{
						if(i == 0)
							System.out.print("\n" + list.get(i));
						else
							System.out.print(" + " + list.get(i));
					}
				}
			}
			if(list.get(index) < n - maxSize + 2)
			{
				System.out.println("\na" + list);
				list.set(index, list.get(index) + 1);
				displayPartitions(n, list);
			}
			else
			{
				System.out.println("\nb" + list);
				list.set(index, 1);
				index--;
				list.set(index, list.get(index) + 1);
				if(list.get(index) != n - maxSize + 2)
				{
					index = list.size() - 1;
					list.set(index, 1);
				}
				/*if(index == 0)
					if(list.get(index + 1) >= list.get(index))
						displayPartitions(n, list);
				else
					if(list.get(index) >= list.get(index - 1))
						displayPartitions(n, list);*/
				displayPartitions(n, list);
			}
		}
	}
	
}
