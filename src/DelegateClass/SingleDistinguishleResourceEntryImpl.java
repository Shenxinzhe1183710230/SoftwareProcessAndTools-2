package DelegateClass;

import DelegateInterface.EntryInterface.SingleDistinguishleResourceEntry;

public class SingleDistinguishleResourceEntryImpl<R> implements SingleDistinguishleResourceEntry<R> {
	private final R resourse;

	public SingleDistinguishleResourceEntryImpl(R resourse) {
		super();
		this.resourse = resourse;
	}

	@Override
	public R getResourse() {
		// TODO Auto-generated method stub
		return resourse;
	}

}