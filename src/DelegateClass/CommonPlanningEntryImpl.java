package DelegateClass;

//��B3��֧�Ͻ����޸�

import DelegateInterface.EntryState;
import DelegateInterface.PlanningEntry;
import DelegateInterface.EntryState.state;

public class CommonPlanningEntryImpl<R> implements PlanningEntry<R> {

	private EntryState state;
	private final String name;

	public CommonPlanningEntryImpl(String name, EntryState state) {
		super();
		this.name = name;
		this.state = state;
	}

	@Override
	public void Begin() {// ��ʼ����ʼ����Դ
		// TODO Auto-generated method stub
		state.setState(EntryState.state.RUNNING);
	}

	@Override
	public void Cancel() {
		// TODO Auto-generated method stub
		state.setState(EntryState.state.CANCELLED);
	}

	@Override
	public void Compelete() {
		// TODO Auto-generated method stub
		state.setState(EntryState.state.ENDED);
	}

	@Override
	public String GetPlanningEntryName() {
		// TODO Auto-generated method stub
		return new String(name);
	}

	@Override
	public state GetNowstate() {// ��ȡ��ǰ״̬
		// TODO Auto-generated method stub
		return state.getState();
	}

	@Override
	public EntryState getEntryState() {
		// TODO Auto-generated method stub
		return state;
	}
}
