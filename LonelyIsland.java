/* NIM/Nama  : 13517137/Vincent Budianto
 * Nama File : LonelyIsland.java
 * Topik     : Tugas Kecil II IF2211 - Strategi Algoritma / Penyelesaian Lonely Island dengan Algoritma Decrease and Conquer
 * Tanggal   : 25 Februari 2019
 * Deskripsi : Source code  menggunakan strategi algoritma decrease and conquer */

import java.io.*;
import java.util.*;
import java.lang.*;

public class LonelyIsland implements Runnable
{
	//Kamus Global
	static int m, n, r, from, to, length;
	static long count;
	static Map Chart;
	static String arg1, arg2;
	static int[] Check;
	static Set<Integer> lonelyIsland;
	static Set<Integer> result;
	static Set<Integer> route;
	static PrintWriter outFile;
	static Scanner inFile;

	//Class Map
	static class Map
	{
		//Kamus Map
		private LinkedList<Integer> Bridge[];

		Map(int a)
		/* ctor dengan parameter */
		{
			//Kamus
			int i;

			//Algoritma
			Bridge = new LinkedList[a];

			for (i = 0; i < a; i++)
			{
				Bridge[i] = new LinkedList();
			}
		}

		void addBridge(int from, int to)
		/* Prosedur untuk menambahkan jembatan satu arah dari suatu island ke island lain */
		{
			//Kamus

			//Algoritma
			Bridge[from].add(to);
		}

		void stuck(int start)
		/* Prosedur untuk mencari island yang tidak memiliki jembatan ke island lain */
		{
			//Kamus
			int i;

			//Algoritma
			for (i = 1; i <= n; i++)
			{
				if (Bridge[i].isEmpty())
				{
					lonelyIsland.add(i);
				}
			}
		}
	}

	static void printTrack(int from, int to)
	/* Prosedur untuk menampilkan seluruh rute yang dapat diambil oleh pemain */
	{
		//Kamus

		//Algoritma
		Check = new int[n];
		route.add(r);
		printRoute(from, to);
	}

	static void printRoute(int from, int to)
	/* Prosedur untuk menampilkan rute yang dapat diambil oleh pemain dari suatu pulau */
	{
		//Kamus
		
		//Algoritma
		Check[from - 1] = 1;

		if (from == to)
		{
			result.add(to);
			count++;
			
			Iterator i = route.iterator();
			
			System.out.print("Track " + count + ": ");
			System.out.print(i.next());

			while (i.hasNext())
			{
				System.out.print(" -> " + i.next());
			}

			System.out.println("\n");
			
			if (length == 1)
			{
				try
				{
					outFile = new PrintWriter(new FileWriter("output.txt", true));
					
					Iterator j = route.iterator();
					
					outFile.println();
					outFile.print("Track " + count + ": ");
					outFile.print(j.next());
					
					while (j.hasNext())
					{
						outFile.print(" -> " + j.next());
					}
					
					outFile.println();
					
					outFile.close();
				}
				catch (Exception e)
				{
				}
			}
			else if (length == 2)
			{
				try
				{
					outFile = new PrintWriter(new FileWriter(arg2, true));
					
					Iterator j = route.iterator();
					
					outFile.println();
					outFile.print("Track " + count + ": ");
					outFile.print(j.next());
					
					while (j.hasNext())
					{
						outFile.print(" -> " + j.next());
					}
					
					outFile.println();
					
					outFile.close();
				}
				catch (Exception e)
				{
				}
			}	
		}
		else
		{
			for (Integer k: Chart.Bridge[from])
			{
				if (Check[k - 1] == 0)
				{
					route.add(k);
					printRoute(k, to);
					route.remove(k);
				}
			}
		}

		Check[from - 1] = 0;
	}

	//Main Program
	public static void main(String[] args)
	/* Thread Constructor */
	{
		new Thread(null, new LonelyIsland(), "", 1 << 26).start();
		length = args.length;
		
		if (length == 1)
		{
			arg1 = args[0];
		}
		else if (length == 2)
		{
			arg1 = args[0];
			arg2 = args[1];
		}
	}
	
	//Thread Program
	public void run()
	{
		//Kamus
		int s;
		float time, startTime, stopTime;

		//Algoritma
		lonelyIsland = new TreeSet<Integer>();	//Himpunan lonely island terurut membesar
		result = new TreeSet<Integer>();		//Himpunan solusi terurut membesar
		route = new LinkedHashSet<Integer>();	//Himpunan untuk menyimpan pulau mana yang sudah dikunjungi
		count = 0;								//jumlah track menuju lonely island
		m = 0;									//jumlah bridge
		n = 0;									//jumlah island
		r = 0;									//start island
		from = 0;								//island asal
		to = 0;									//island tujuan

		//Proses Baca File
		try
		{
			if (length == 0)
			{
				inFile = new Scanner(new File("input.txt"));
			}
			else
			{
				inFile = new Scanner(new File(arg1));
			}

			n = inFile.nextInt();
			m = inFile.nextInt();
			r = inFile.nextInt();

			Chart = new Map(n + 1);

			while (inFile.hasNext() && (m != 0))
			{
				from = inFile.nextInt();
				to = inFile.nextInt();

				Chart.addBridge(from, to);
				m--;
			}

			inFile.close();
		}
		catch (Exception e)
		{
		}

		//Start time counter
		startTime = System.nanoTime();

		//Mencari island dimana pemain terjebak
		Chart.stuck(r);

		//Menuliskan island dimana pemain terjebak
		if (lonelyIsland.size() == 0)
		{
			System.out.println("terdapat " + result.size() + " pulau dimana pemain dapat terjebak jika pemain mulai dari pulau " + r + "\n");
			System.out.println("semua pulau saling terhubung\n");
		}
		else
		{
			//Inisialisasi file eksternal
			if (length == 1)
			{
				try
				{
					outFile = new PrintWriter("output.txt", "UTF-8");
					
					if (lonelyIsland.size() != 0)
					{
						outFile.println("rute yang dapat diambil pemain dari pulau " + r + ":");
					}
					
					outFile.close();
				}
				catch (Exception e)
				{
				}
			}
			else if (length == 2)
			{
				try
				{
					outFile = new PrintWriter(arg2, "UTF-8");
					
					if (lonelyIsland.size() != 0)
					{
						outFile.println("rute yang dapat diambil pemain dari pulau " + r + ":");
					}
					
					outFile.close();
				}
				catch (Exception e)
				{
				}
			}
			
			//Menuliskan rute yang dapat diambil dari start island
			System.out.println("rute yang dapat diambil pemain dari pulau " + r + ":\n");
			
			Iterator<Integer> k = lonelyIsland.iterator();
			
			s = k.next();
			printTrack(r, s);

			while (k.hasNext())
			{
				s = k.next();
				printTrack(r, s);
			}
			
			Iterator j = result.iterator();
			
			System.out.print("terdapat " + result.size() + " pulau dimana pemain dapat terjebak jika pemain mulai dari pulau " + r + " yaitu di pulau " + j.next());

			while (j.hasNext())
			{
				System.out.print(" atau pulau " + j.next());
			}
			
			System.out.println("\n");
		}
		
		//Proses penulisan ke file eksternal
		if (length == 1)
		{
			try
			{
				if (result.size() == 0)
				{
					outFile = new PrintWriter("output.txt", "UTF-8");
					outFile.println("terdapat " + result.size() + " pulau dimana pemain dapat terjebak jika pemain mulai dari pulau " + r);
					outFile.println();
					outFile.println("semua pulau saling terhubung");
				}
				else
				{
					outFile = new PrintWriter(new FileWriter("output.txt", true));
					
					Iterator i = result.iterator();
					
					outFile.println();
					outFile.print("terdapat " + result.size() + " pulau dimana pemain dapat terjebak jika pemain mulai dari pulau " + r + " yaitu di pulau yaitu di pulau " + i.next());

					while (i.hasNext())
					{
						outFile.print(" atau pulau " + i.next());
					}
				}
				
				outFile.close();
			}
			catch (Exception e)
			{
			}
		}
		else if (length == 2)
		{
			try
			{
				if (result.size() == 0)
				{
					outFile = new PrintWriter(arg2, "UTF-8");
					outFile.println("terdapat " + result.size() + " pulau dimana pemain dapat terjebak jika pemain mulai dari pulau " + r);
					outFile.println();
					outFile.println("semua pulau saling terhubung");
				}
				else
				{
					outFile = new PrintWriter(new FileWriter(arg2, true));
					
					Iterator i = result.iterator();
					
					outFile.println();
					outFile.print("terdapat " + result.size() + " pulau dimana pemain dapat terjebak jika pemain mulai dari pulau " + r + " yaitu di pulau yaitu di pulau " + i.next());

					while (i.hasNext())
					{
						outFile.print(" atau pulau " + i.next());
					}
					
				}

				outFile.close();
			}
			catch (Exception e)
			{
			}
		}

		//Stop time counter
		stopTime = System.nanoTime();

		time = (stopTime - startTime) / 1000000000;
		System.out.printf("Execution time : %.5f seconds", time);
	}
}
