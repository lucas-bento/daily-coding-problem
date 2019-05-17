//This problem was asked by Google.

//An XOR linked list is a more memory efficient doubly linked list.
//Instead of each node holding next and prev fields, it holds a field named both, which is an XOR of the next node and the previous node. 
//Implement an XOR linked list; it has an add(element) which adds the element to the end, and a get(index) which returns the node at index.

//If using a language that has no pointers (such as Python), you can assume you have access to get_pointer and dereference_pointer functions that converts between nodes and memory addresses.

#include <stdio.h>
#include <stdlib.h>






int main(void)
{
	struct element 
	{
		element* xored_pointers;
		int value;
	};
	
	struct element first;
	first.value = 0;
	
	struct element* previous = malloc(sizeof(element));

	for (int i = 1; i<90; i++) {
		struct element current;	
				
		element* actual_pointer = malloc(sizeof(element));


		current.value = i;
		current.xored_pointers = 
	
		if(i==0) {
			first.xored_pointers=
		}
	}

	printf("Olá Mundo!\n");
	return 0;
}
