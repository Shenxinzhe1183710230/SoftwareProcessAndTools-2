package DelegateClass;

import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.BlockedStateEntry;
import P1.graph.Graph;

public class BlockedStateEntryImpl implements BlockedStateEntry {
	Graph<EntryState.state> graph = Graph.empty();
	private state Nowstate = state.WAITING;

	public BlockedStateEntryImpl() {
		super();
		graph.set(state.WAITING, state.ALLOCATED, 1);
		graph.set(state.WAITING, state.CANCELLED, 1);
		graph.set(state.ALLOCATED, state.RUNNING, 1);
		graph.set(state.RUNNING, state.ENDED, 1);
		graph.set(state.ALLOCATED, state.CANCELLED, 1);
		graph.set(state.BLOCKED, state.RUNNING, 1);
		graph.set(state.BLOCKED, state.CANCELLED, 1);
		graph.set(state.RUNNING, state.BLOCKED, 1);
	}

	@Override
	public void Blocked() {
		// TODO Auto-generated method stub
		setState(state.BLOCKED);
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