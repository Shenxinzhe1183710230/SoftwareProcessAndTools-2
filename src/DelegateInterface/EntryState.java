package DelegateInterface;

public interface EntryState {
	enum state {
		WAITING, ALLOCATED, RUNNING, BLOCKED, ENDED, CANCELLED
	}// 未分配资源，已分配，已启动，挂起中，已完成，已取消

	public state getState();

	public Boolean setState(state myState);
}
