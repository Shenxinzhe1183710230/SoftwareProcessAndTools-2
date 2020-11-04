package DelegateClass;

import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.UnblockedStateEntry;
import P1.graph.Graph;

public class UnblockedStateEntryImpl implements UnblockedStateEntry {
	Graph<EntryState.state> graph = Graph.empty();
	private state Nowstate = state.WAITING;

	public UnblockedStateEntryImpl() {
		super();
		graph.set(state.WAITING, state.ALLOCATED, 1);
		graph.set(state.WAITING, state.CANCELLED, 1);
		graph.set(state.ALLOCATED, state.RUNNING, 1);
		graph.set(state.RUNNING, state.ENDED, 1);
		graph.set(state.ALLOCATED, state.CANCELLED, 1);
	}

	@Override
	public state getState() {
		// TODO Auto-generated method stub
		return Nowstate;
	}

	@Override
	public Boolean setState(state myState) {
		// TODO Auto-generated method stub
		if (graph.targets(Nowstate).containsKey(myState)) {
			Nowstate = myState;
			return true;
		} else {
			return false;
		}
	}

}