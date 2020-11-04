package DelegateInterface;

import DelegateInterface.EntryState.state;

public interface PlanningEntry<R> {

	public void Begin();

	public void Cancel();

	public void Compelete();

	public String GetPlanningEntryName();

	public state GetNowstate();

	public DelegateInterface.EntryState getEntryState();
}
