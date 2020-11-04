package DelegateClass;

import java.util.ArrayList;
import java.util.List;

import DelegateInterface.EntryInterface.MultipleDustinguishResourseEntry;
import Resourse.TrainCarriage;

public class MultipleDustinguishResourseEntryImpl<R> implements MultipleDustinguishResourseEntry<R> {
	private List<R> list;
	Boolean flagBoolean = false;

	public MultipleDustinguishResourseEntryImpl(List<R> list) {
		super();
		this.list = list;
	}

	@Override
	public List<R> getResourses() {
		// TODO Auto-generated method stub
		return new ArrayList<R>(list);
	}

	@Override
	public Boolean addResourse(R r) {
		// TODO Auto-generated method stub
		if (!resourseSame()) {
			list.add(r);
			return true;
		}
		return false;
	}

	@Override
	public void removeResourse(R r) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(r)) {
				list.remove(i);
				break;
			}
		}
	}

	@Override
	public R getResourse(String trainNumber) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			if (((TrainCarriage) list.get(i)).getNumber().equals(trainNumber)) {
				return list.get(i);
			}
		}
		return null;
	}

	/**
	 * 检查一个计划项之内的所有资源是否冲突
	 * 
	 * @param list
	 * @return 冲突返回true
	 */
	@Override
	public Boolean resourseSame() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).equals(list.get(j))) {
					return true;
				}
			}
		}
		return false;
	}
}