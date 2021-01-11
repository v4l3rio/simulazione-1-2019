package e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;

public class GraphFactoryImpl implements GraphFactory {

	@Override
	public <X> Graph<X> createDirectedChain(List<X> nodes) {
		Graph<X> g = new GraphImpl<>();

		for (int i = 0; i < nodes.size() - 1; i++) {
			g.addEdges(new Pair<X, X>(nodes.get(i), nodes.get(i + 1)));
		}

		return g;
	}

	@Override
	public <X> Graph<X> createBidirectionalChain(List<X> nodes) {
		Graph<X> g = new GraphImpl<>();

		for (int i = 0; i < nodes.size() - 1; i++) {
			g.addEdges(new Pair<X, X>(nodes.get(i), nodes.get(i + 1)));
			g.addEdges(new Pair<X, X>(nodes.get(i + 1), nodes.get(i)));
		}
		return g;
	}

	@Override
	public <X> Graph<X> createDirectedCircle(List<X> nodes) {
		Graph<X> g = createDirectedChain(nodes);
		g.addEdges(new Pair<X, X>(nodes.get(nodes.size() - 1), nodes.get(0)));
		return g;
	}

	@Override
	public <X> Graph<X> createBidirectionalCircle(List<X> nodes) {
		Graph<X> g = createBidirectionalChain(nodes);
		g.addEdges(new Pair<X, X>(nodes.get(nodes.size() - 1), nodes.get(0)));
		g.addEdges(new Pair<X, X>(nodes.get(0), nodes.get(nodes.size() - 1)));
		return g;
	}

	@Override
	public <X> Graph<X> createDirectedStar(X center, Set<X> nodes) {
		Graph<X> g = new GraphImpl<>();
		Iterator<X> iter = nodes.iterator();
		while (iter.hasNext()) {
			g.addEdges(new Pair<X, X>(center, iter.next()));
		}
		return g;
	}

	@Override
	public <X> Graph<X> createBidirectionalStar(X center, Set<X> nodes) {
		Graph<X> g = new GraphImpl<>();
		Iterator<X> iter = nodes.iterator();
		while (iter.hasNext()) {
			X currentNode = iter.next();
			g.addEdges(new Pair<X, X>(center, currentNode));
			g.addEdges(new Pair<X, X>(currentNode, center));
		}
		return g;
	}

	@Override
	public <X> Graph<X> createFull(Set<X> nodes) {
		Graph<X> g = new GraphImpl<>();
		List<X> nodesList = new ArrayList<>(nodes);
		
		for(int j=0; j< nodesList.size();j++) {
			for(int i = 0;i<nodesList.size(); i++) {
				if(i!=j) {
					g.addEdges(new Pair<X, X>(nodesList.get(j), nodesList.get(i)));
				}
			}
		}
		return g;
	}

	@Override
	public <X> Graph<X> combine(Graph<X> g1, Graph<X> g2) {
		Graph<X> g = new GraphImpl<>();
		
		Set<Pair<X,X>> g1Edges=g1.getEdgesStream().collect(Collectors.toSet());
		Set<Pair<X,X>> g2Edges=g2.getEdgesStream().collect(Collectors.toSet());
		
		Iterator<Pair<X, X>> iterG1 = g1Edges.iterator();
		Iterator<Pair<X, X>> iterG2 = g2Edges.iterator();
		
		while(iterG1.hasNext()) {
			g.addEdges(iterG1.next());
		}
		while(iterG2.hasNext()) {
			g.addEdges(iterG2.next());
		}
		
		
		return g;
	}

}
