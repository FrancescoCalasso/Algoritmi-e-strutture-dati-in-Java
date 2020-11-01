package model.struct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * La classe DAG rappresenta un grafo diretto aciclico mediante liste di adiacenza.
 * In particolare si e' voluto dare un'implementazione che utilizzasse classi
 * standard di java.util.
 * Di conseguenza:
 * 1. la lista dei nodi e' rappresentata da una HashMap per poter accedere ai nodi in
 * tempo costante
 * 2. la lista dei nodi adiacenti e' rappresentata da un HashSet di nodi orientati, in
 * modo tale da poter verificare/accedere al nodo adiacente in tempo costante.
 * Anziche' rappresentare il nodo adiacente e il peso dell'arco si e' preferito
 * rappresentare l'arco completo.
 *
 */

public class GrafoOrdinato extends Grafo {

    private int time = 0;
    private Map<Object,Integer> endValues = new HashMap<Object,Integer>();
    private Map<Object,Integer> startValues = new HashMap<Object,Integer>();



    public boolean add(ArcoOrdinato a) {
        return add(a.getFrom(),a.getTo(),a.getValue());
    }

    public boolean add(Object x, Object y, Object value) {
        boolean flag = false, flag1 = false;
        if (!nodi.containsKey(x))
            add(x);
        if (!nodi.containsKey(y))
            add(y);
        ArcoOrdinato a = new ArcoOrdinato(x,y,value);
        flag = ( (Set<Arco>) nodi.get(x) ).add(a);
        flag1 =( (Set<Arco>) nodi.get(y) ).add(a);
        flag = flag && flag1;
        if (flag)
            nArchi++;
        return flag;
    }

    /**
     * Restituisce l'insieme di archi incidenti entranti nel nodo nodo,
     * se nodo e' presente nel grafo, null altrimenti
     *
     * @param nodo nodo di cui si vuole conoscere l'insieme di archi incidenti
     * @return l'insieme di archi incidenti sul nodo nodo,
     * se nodo e' presente nel grafo null altrimenti
     */
    public Set<ArcoOrdinato> getInEdgeSet(Object nodo) {
        if (nodi.containsKey(nodo)){
            //System.out.println("building inset for "+nodo);
            Set<ArcoOrdinato> res = new HashSet<ArcoOrdinato>();
            for (Arco o : getEdgeSet(nodo)) {
                ArcoOrdinato a = (ArcoOrdinato) o;
                if (a.getTo().equals(nodo)){
                    res.add(a);
                }
            }
            return res;

        } //se il nodo e' presente nel grafo
        else
            return null;
    }

    /**
     * Restituisce l'insieme di archi incidenti uscenti dal nodo nodo,
     * se nodo e' presente nel grafo, null altrimenti
     *
     * @param nodo nodo di cui si vuole conoscere l'insieme di archi incidenti
     * @return l'insieme di archi incidenti sul nodo nodo,
     * se nodo e' presente nel grafo null altrimenti
     */
    public Set<ArcoOrdinato> getOutEdgeSet(Object nodo) {
        if (nodi.containsKey(nodo)){
            Set<ArcoOrdinato> res = new HashSet<ArcoOrdinato>();
            for (Arco  o : getEdgeSet(nodo)) {
                ArcoOrdinato a = (ArcoOrdinato) o;
                if (a.getFrom().equals(nodo)){
                    res.add(a);
                }
            }
            return res;

        } //se il nodo e' presente nel grafo
        else
            return null;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        Object nodo;
        ArcoOrdinato a;
        Iterator arcoI;
        Iterator nodoI = nodi.keySet().iterator();
        while (nodoI.hasNext()) {
            arcoI = ((Set) nodi.get( nodo = nodoI.next() )).iterator();
            out.append("Nodo " + nodo.toString() + ": ");
            while (arcoI.hasNext()) {
                a = (ArcoOrdinato) arcoI.next();
                //out.append( ((a.x == nodo ) ? a.y.toString() : a.x.toString()) + "("+a.value.toString()+"), ");
                out.append(a.toString()+", ");
            }
            out.append("\n");
        }
        return out.toString();
    }



    /**
     * Restituisce un ordine topologico di un DAG,
     * eseguendo una DFS.
     *
     * @return una ordine topologico del grafo partendo da startingNode
     */
    public List<Object> getTopologicalOrder(){
        System.out.println("metodo da implementare");
        return null;
    }

    /**
     * Data una map di nodi ad interi restituisce (ed elimina dalla map) il nodo che ha valore piu' alto.
     *
     * @return nodo (object) con valore piu' alto. Il node viene eliminato dalla map.
     */
    private Object pullMax(Map<Object,Integer> m) {
        Object best = null;
        Integer bestValue = 0;
        for (Iterator iterator = m.keySet().iterator(); iterator.hasNext();) {
            Object curr = iterator.next();
            Integer currValue = m.get(curr);
            if((best == null) || (currValue > bestValue)){
                best = curr;
                bestValue = currValue;
            }
        }
        m.remove(best);
        return best;
    }

    /**
     * Metodo ricorsivo che esegue la dfs dal nodo node
     *
     * @param node nodo da cui partire per la visita DFS
     * @exception IllegalArgumentException if the directed graph contains a cycle (i.e., it is not a DAG)
     */
    private void dfs(Object node) {

        System.out.println("metodo da implementare");

    }

//    public static void main(String[] args) {
 //       DAG g = new DAG();
 //       g.add(new ArcoOrdinato("e","d",new Integer(1)));
  //      g.add(new ArcoOrdinato("d","f",new Integer(1)));
  //      g.add(new ArcoOrdinato("d","b",new Integer(3)));
  //      g.add(new ArcoOrdinato("d","c",new Integer(4)));
 //       g.add(new ArcoOrdinato("b","c",new Integer(2)));

 //       System.out.println("Il grafo G e':\n" + g);
 //       System.out.println("L'insieme di archi e': " + g.getEdgeSet());

  //      System.out.println(g.getTopologicalOrder());
  //      System.out.println();

        //g2 contiene un ciclo
   //     DAG g2 = new DAG();
    //    g2.add(new ArcoOrdinato("e","d",new Integer(1)));
   //     g2.add(new ArcoOrdinato("d","f",new Integer(1)));
   //     g2.add(new ArcoOrdinato("d","b",new Integer(3)));
    //    g2.add(new ArcoOrdinato("c","d",new Integer(4)));
    //    g2.add(new ArcoOrdinato("b","c",new Integer(2)));

    //    System.out.println("Il grafo G2 e':\n" + g2);
    //    System.out.println("L'insieme di archi e': " + g2.getEdgeSet());

    //    System.out.println(g2.getTopologicalOrder());
     //   System.out.println();

   // }



}