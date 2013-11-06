primeset-100000:	primeset-100000-01	primeset-100000-03	primeset-100000-05	primeset-100000-075

primeset-100000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.1 100000

primeset-100000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.3 100000

primeset-100000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.5 100000

primeset-100000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.75 100000


primeset-10000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.1 10000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.3 10000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.5 10000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.75 10000

primeset-1000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.1 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.3 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.5 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.75 1000


primeset-100:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.1 100
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.3 100
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.5 100
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.75 100


sorted-arrayset-100:	sorted-arrayset-100-01	sorted-arrayset-100-03	sorted-arrayset-100-05	sorted-arrayset-100-075

sorted-arrayset-100-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.1 100

sorted-arrayset-100-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.3 100

sorted-arrayset-100-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.5 100

sorted-arrayset-100-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.75 100


sorted-arrayset-1000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.1 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.3 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.5 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.75 1000

sorted-arrayset-10000:	sorted-arrayset-10000-01	sorted-arrayset-10000-03	sorted-arrayset-10000-05		sorted-arrayset-10000-075

sorted-arrayset-10000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.1 10000

sorted-arrayset-10000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.3 10000

sorted-arrayset-10000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.5 10000

sorted-arrayset-10000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.75 10000


sorted-arrayset-100000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.1 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.3 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.5 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.75 100000

sorted-arrayset-100000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.75 100000

unsorted-arrayset-100:	unsorted-arrayset-100-01	unsorted-arrayset-100-03	unsorted-arrayset-100-05	unsorted-arrayset-100-075

unsorted-arrayset-100-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.1 100

unsorted-arrayset-100-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.3 100

unsorted-arrayset-100-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.5 100

unsorted-arrayset-100-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.75 100


unsorted-arrayset-1000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.1 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.3 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.5 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.75 1000

unsorted-arrayset-10000:	unsorted-arrayset-10000-01	unsorted-arrayset-10000-03	unsorted-arrayset-10000-05		unsorted-arrayset-10000-075

unsorted-arrayset-10000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.1 10000

unsorted-arrayset-10000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.3 10000

unsorted-arrayset-10000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.5 10000

unsorted-arrayset-10000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.75 10000


unsorted-arrayset-100000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.1 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.3 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.5 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.75 100000

sorted-treeset-100:	sorted-treeset-100-01	sorted-treeset-100-03	sorted-treeset-100-05	sorted-treeset-100-075

sorted-treeset-100-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.1 100

sorted-treeset-100-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.3 100

sorted-treeset-100-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.5 100

sorted-treeset-100-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.75 100


sorted-treeset-1000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.1 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.3 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.5 1000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.75 1000

sorted-treeset-10000:	sorted-treeset-10000-01	sorted-treeset-10000-03	sorted-treeset-10000-05		sorted-treeset-10000-075

sorted-treeset-10000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.1 10000

sorted-treeset-10000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.3 10000

sorted-treeset-10000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.5 10000

sorted-treeset-10000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.75 10000


sorted-treeset-100000:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.1 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.3 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.5 100000
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.75 100000



hashset-100000:	hashset-100000-01 hashset-100000-03	hashset-100000-05	hashset-100000-075

hashset-100000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.1 100000

hashset-100000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.3 100000

hashset-100000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.5 100000

hashset-100000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.75 100000



hashset-10000:		hashset-10000-01	hashset-10000-03	hashset-10000-05	hashset-10000-075

hashset-10000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.1 10000

hashset-10000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.3 10000

hashset-10000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.5 10000

hashset-10000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.75 10000


hashset-1000:	hashset-1000-01		hashset-1000-03		hashset-1000-05		hashset-1000-075

hashset-1000-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.1 1000

hashset-1000-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.3 1000

hashset-1000-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.5 1000

hashset-1000-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.75 1000

hashset-100:	hashset-100-01		hashset-100-03		hashset-100-05		hashset-100-075

hashset-100-01:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.1 100

hashset-100-03:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.3 100

hashset-100-05:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.5 100

hashset-100-075:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.75 100


primeset-sparse:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --primeset 0.00003 100000

sorted-treeset-sparse:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-treeset 0.00003 100000

sorted-arrayset-sparse:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --sorted-arrayset 0.00003 100000

unsorted-arrayset-sparse:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --unsorted-arrayset 0.00003 100000

hashset-sparse:
	scala -J-Xmx5g -J-Xms5g -J-Xss8192m  -classpath bin org.ucombinator.datastructure.benchmark.CompareTime3 --time --hashset 0.00003 100000

	
100:	primeset-100	sorted-treeset-100	sorted-arrayset-100  hashset-100

1000:	primeset-1000	sorted-treeset-1000	sorted-arrayset-1000  hashset-1000

10000:	primeset-10000	sorted-treeset-10000 sorted-arrayset-10000  hashset-10000

100000: primeset-100000	sorted-treeset-100000	sorted-arrayset-100000		hashset-100000

sparse:	primeset-sparse	sorted-treeset-sparse	sorted-arrayset-sparse   hashset-sparse