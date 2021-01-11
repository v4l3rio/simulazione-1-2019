package e1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class GraphImpl<X> implements Graph<X> {
	
	
	private Set<Pair<X,X>> edges = new HashSet<>();
	
	
	public void addEdges(Pair<X,X> arch) {
		edges.add(arch);
	}
	

	@Override
	public Set<X> getNodes() {
		Set<X> nodes = new HashSet<>();
		for(Pair<X,X> k : edges) {
			nodes.add(k.getX());
			nodes.add(k.getY());
		}
		return nodes;
	}

	@Override
	public boolean edgePresent(X start, X end) {
		for(Pair<X,X> k : edges) {
			if(k.getX().equals(start) && k.getY().equals(end)) {
				return true;
			}
		}
		return false;
		
	}

	@Override
	public int getEdgesCount() {
		return edges.size();
	}

	@Override
	public Stream<Pair<X, X>> getEdgesStream() {
		return edges.stream();
	}

}
