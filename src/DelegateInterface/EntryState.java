package DelegateInterface;

public interface EntryState {
	enum state {
		WAITING, ALLOCATED, RUNNING, BLOCKED, ENDED, CANCELLED
	}// δ������Դ���ѷ��䣬�������������У�����ɣ���ȡ��

	public state getState();

	public Boolean setState(state myState);
}
