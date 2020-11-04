package model.struct.Grafo;
import java.util.*;

/**
  * Questa classe rappresenta un grafo mediante liste di adiacenza:
  * - la lista dei nodi e' rappresentata da una HashMap;
  * - la lista dei nodi adiacenti e' rappresentata da un Set di archi.
  */
public class GrafoList {

  HashMap<Object,Set<Arco>> nodi;
  int nArchi;

  public GrafoList() {
    nodi = new HashMap<>();
    nArchi = 0;
  }

  /**
   * Ricerca un nodo nel grafo
   *
   * @param node nodo da cercare
   * @return true se il nodo è stato trovato, false altrimenti
   */
  public boolean searchNode(Object node) {

    if(nodi.containsKey(node)) {

      return true;

    } else {

      return false;

    }

  }

  /**
   * Aggiunge un nodo al grafo con il relativo Set di archi adiacenti
   * 
   * @param node nodo da inserire
   */
  public void addNode(Object node) {
    if (!nodi.containsKey(node)) {
      Set<Arco> edges = new HashSet<>();
      nodi.put(node, edges);
    }
  }

  /**
   * Rimuove il nodo "node" dal grafo con il relativo Set di archi adiacenti
   * 
   * @param node nodo da rimuovere
   */
  public void removeNode(Object node) {
    if (nodi.containsKey(node)) {
      Iterator<Arco> arcoInX = nodi.get(node).iterator();
      Arco a;
      Object y;
      while (arcoInX.hasNext()) {
        a = arcoInX.next();
        y = ( a.startingNode.equals(node) ) ? a.endingNode : a.startingNode;

        if ( nodi.get(y).remove(a) )
          nArchi--;
      }

      nodi.remove(node);

    }
  }


  /**
   * Aggiunge un arco con nodo iniziale, nodo finale e relativo peso
   * 
   * @param starting nodo iniziale
   * @param ending nodo finale
   * @param value peso dell'arco
   * @return true se rimozione avvenuta con successo, false altrimenti
   */
  public boolean addEdge(Object starting, Object ending, Object value) {

    boolean start = false, end = false;

    if (!nodi.containsKey(starting))
      addNode(starting);

    if (!nodi.containsKey(ending))
      addNode(ending);

    Arco a = new Arco(starting, ending, value);

    start = (nodi.get(starting)).add(a);
    end =(nodi.get(ending)).add(a);
    start = start && end;

    if (start)
      nArchi++;

    return start;

  }

  /**
   * Rimuove l'arco dal grafo
   *
   * @param arco arco da rimuovere
   * @return true se rimozione avvenuta con successo, false altrimenti
   */
  public boolean removeEdge(Arco arco) {

    boolean start = false,  end = false;

    if (nodi.containsKey(arco.startingNode) && nodi.containsKey(arco.endingNode)) {
      start = nodi.get(arco.startingNode).remove(arco);
      end = nodi.get(arco.endingNode).remove(arco);
    }

    return start || end;
  }

  /**
   * Visita in ampiezza, con partenza da un nodo iniziale
   *
   * @param startingNode nodo iniziale della visita
   */
  public void breadthFirstSearch(Object startingNode) {
    Queue<Object> q = new LinkedList<>();
    Set<Object> nodes = new HashSet<>(nodi.keySet());

    q.add(startingNode);
    nodes.remove(startingNode);

    for (Iterator<Object> itm = this.nodi.keySet().iterator(); itm.hasNext();) {

      while(!q.isEmpty()){
        Object node = q.poll();
        visit(node);
        Set<Arco> adjEdges = nodi.get(node);
        for (Iterator<Arco> iterator = adjEdges.iterator(); iterator
                .hasNext();) {
          Arco a = iterator.next();
          if (nodes.contains(a.startingNode)){
            q.add(a.startingNode);
            nodes.remove(a.startingNode);
          }
          if (nodes.contains(a.endingNode)){
            q.add(a.endingNode);
            nodes.remove(a.endingNode);
          }
        }
      }

      Object nextStartingNode = itm.next();
      if(nodes.contains(nextStartingNode)){
        startingNode = nextStartingNode;
        nodes.remove(startingNode);
        q.add(startingNode);
      }


    }

  }

  /**
   * Visita in profondità, conn partenza da un nodo iniziale
   *
   * @param startingNode nodo da cui inizia la visita
   */
  public void depthFirstSearch(Object startingNode) {
    Set<Object> nodes = new HashSet<>(nodi.keySet());
    Object node = startingNode;
    DFS(node, nodes);
    while(!nodes.isEmpty()){
      node = nodes.iterator().next();
      DFS(node, nodes);
    }
  }

  /**
   * Funzione ausiliaria della visita in profondità, esegue una visita ricorsiva partendo dal nodo next
   * ed utilizzando come marcatura nodes
   *
   * @param next nodo da cui iniziare la visita ricorsiva
   * @param nodes marcatura
   */
  private void DFS(Object next, Set nodes) {

    visit(next);
    nodes.remove(next);

    Set<Arco> adjEdges = nodi.get(next);
    Iterator<Arco> it = adjEdges.iterator();

    while(it.hasNext()){

      Arco a = it.next();

      if (nodes.contains(a.startingNode)){
        DFS(a.startingNode, nodes);
      }

      if (nodes.contains(a.endingNode)){
        DFS(a.endingNode, nodes);
      }

    }
  }

  /**
   * Stampa di un nodo
   *
   * @param node nodo da stampare
   */
  private static void visit(Object node) {
    System.out.println(node);
  }

}