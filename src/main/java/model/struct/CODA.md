# Coda

Una coda è una struttura dati in cui è possibile solo **aggiungere** in coda e **cancellare** dalla testa della lista.

Possiede le seguenti operazioni:

* **Enqueue(Q,e)**: aggiunge *e* alla fine della coda
* **Dequeue(Q): restituisce l’elemento all’inizio della coda, cancellandolo dalla stessa
* **Empty(Q)**: restituisce *true* se la coda `e vuota

## Rappresentazione

Come nel caso della pila, è possibile realizzare una coda sia con una lista che con un vettore.

## Realizzazione con vettore

Lo stoccaggio dei dati è effettuato in un vettore *A*, lungo *l*, con indice del primo elemento 0: teniamo traccia della posizione dove va inserito un nuovo elemento e di quella dell’elemento piu` vecchio con due indici **tail** e **head** e del numero di elementi contenuti *n*.

* **Enqueue(Q,e)**: se *n < l*, inserisci l’elemento in A[tail], incrementa *n* e *tail*, altrimenti segnala l’errore
* **Dequeue(Q)**: se *n* > 0, restituisci A[head] corrente, decrementa *n*, incrementa *head*
* **Empty(S)**: Restituisci n = 0 ?

## Realizzazione con lista

Lo stoccaggio dei dati è effettuato negli elementi della lista: teniamo traccia dell’ultimo elemento della lista (oltre al primo) con un puntatore *tail*.

* **Enqueue(Q,e)**: inserisci l’elemento *e* in coda alla lista, aggiornando *tail*
* **Dequeue(Q)**: restituisci l’elemento in testa se diverso da NIL, cancellandolo e aggiornando *head*
* **Empty(S)**: Restituisci *head* = *tail* ?

## Complessità delle operazioni