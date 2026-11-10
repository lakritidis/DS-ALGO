package hashing;

import java.util.Random;

import items.Item;
import comparator.Comparator;

public class hashOperator3 {
	private int m;	// σταθερά συναρτήσεως
	private int p = 65537;
	private int s;	// μέγεθος πίνακα κατακερματισμού
	private Comparator c;
	private static Random ran = new Random(230870);

	public hashOperator3(int S, Comparator comp) {
		s = m = S;
		c = comp;
	}

	public hashOperator3(int k, int S) {
		m = k;
		s = S;
	}

	public hashOperator3(int k, int S, Comparator comp) {
		m = k;
		s = S;
		c = comp;
	}

	public hashOperator3(int k, int S, int Pi, Comparator com) {
		m = k;
		s = S;
		p = Pi;
		c = com;
	}

	private int randomKey() {
		return 1 + (ran.nextInt(30000) % (p-1));
	}	

	public int hashTo(Object k) {
	//	System.out.print("m:"+m+"s:"+s);
	//	System.out.println("List No"+((Integer)k).intValue()%m);
		return (((((((Integer)k).intValue() & 65535) * m)) % p) % s);
	}

	public int hashTo(Object k, int M, int S) {
	//	System.out.println("List No"+((Integer)k).intValue()%m);
	//System.out.print("m:"+M+"s:"+S);
	return (((((((Integer)k).intValue() & 0x0000ffff) * M)) % p) % S);
	}

	public Comparator Comparator() {
		return c;
	} 

	public boolean equal(Object x, Object y) {
		return c.equal(x, y);
	}

	public void selectHashFunction(int S, Item[] L) {
		boolean [] test = new boolean[S];
		int i, j, M;

		while (true) {
			new_key:{
				//	System.out.print("new start"+S); 
				for (i = 0; i < S; i++) {
					test[i] = false; 
				}

				M = randomKey();	
				for (i = 0; i < L.length; i++) {
					if (L[i]!= null) {
						j = hashTo(L[i].getKey(), M, S);
					// System.out.print("k"+L[i].getKey() +"p"+j);
						if (test[j]) {
							break new_key;
						} else {
							test[j] = true;
						}
					}
				}
				break;
			}
		}
		m = M;
		s = S;
	}

	public int select1LevelHashFunction(int[] b, Item[] L) {
		int i, S = 0, j;
		m = randomKey();
		for (i = 0; i < b.length; i++)
			b[i] = 0;
		for (i = 0; i < L.length; i++)
			if (L[i] == null) continue;
			else b[hashTo(L[i].getKey(), m, b.length)]++;
		for (i = 0; i < b.length; i++) {
		/* Note: here we might imagine S wrapping around, since b[j]
		may in principle be close to sqrt(UINT_MAX). We need to
		check the summation more closely. Actually, since the
		degeneracy_limit is at most ca. 50*sqrt(UINT_MAX) we culd
		probably do with a check that b[j]<=1024 or some other
		suitable value. Then we could check at each point that S is
		not too large. */
		j = (b[i] == 0 ? 2 : 2*b[i]);
		S += 2*j*(j-1);
		} s=b.length;
		return S;
	}

	public int getM() {
		return m;
	}

	/* dict_hash.c */
	/* This is expanded from the pseudo code of Dietz... , 1994 */
	/* Correspondencies:
	Insert(x) -> dict_insert(bottom, x, val)
	RehashAll(x) -> dict_rehash_all(bottom,x)
	Delete(x) -> dict_delete(bottom,x)
	Lookup(x) -> dict_lookup(bottom,x,&val)
	Initialize() -> dict_create(bits)
	Note that we have to carry around the "struct dict_data * bottom".
	*/

	/*#define P 65537
	#define DELETED_FIELD 0x80000000
	#define INDMASK 0x0000ffff
	#else
	#error sorry I only work on 32 bit machines
	#endif
	#define TRADITIONAL_HASH_FUNCTION 1
	#if TRADITIONAL_HASH_FUNCTION == 1
	#define HASH(x,k,s) (((((x) & INDMASK)*(k)) % P) % s)
	#define NEWKEY(bits) (1+(rand() % (P-1)))
	#endif
	/*
	* select_hashfunction selects a perfect hashfunction from [0,b[ to
	* [0,s[ for the set given by the array Lj (of size b). The keys to
	* try will be the ones returned by NEWKEY(0).
	*
	* The caller is responsible for selecting s large enough that the
	* probability of NEWKEY(0) returning a good key is sufficiently high.
	*/
}
