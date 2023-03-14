package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Partitions {
	public static ArrayList<ArrayList<Integer>> partition(int fromDepth, int toDepth, ArrayList<Integer> al){
		if(fromDepth < 2)
			return null;
		if(toDepth > 12)
			toDepth = 12;
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		for(int i = fromDepth; i < toDepth; i++) {
			if(toDepth > 12)
				break;
			ArrayList<ArrayList<Integer>> curPartitions = partition(i, al);
			for(ArrayList<Integer> a : curPartitions) {
				partitions.add(curPartitions.get(i));
			}
		}
		return partitions;
	}
	public static ArrayList<ArrayList<Integer>> partition(int depth, ArrayList<Integer> al) {
		if(depth < 2)
			return null;
		switch(depth) {
		case 2:
			return Util.sortArrayListsByLength(partition2(al));
		case 3:
			return Util.sortArrayListsByLength(partition3(al));
		case 4:
			return Util.sortArrayListsByLength(partition4(al));
		case 5:
			return Util.sortArrayListsByLength(partition5(al));
		case 6:
			return Util.sortArrayListsByLength(partition6(al));
		case 7:
			return Util.sortArrayListsByLength(partition7(al));
		case 8:
			return Util.sortArrayListsByLength(partition8(al));
		case 9:
			return Util.sortArrayListsByLength(partition9(al));
		case 10:
			return Util.sortArrayListsByLength(partition10(al));
		case 11:
			return Util.sortArrayListsByLength(partition11(al));
		case 12:
			return Util.sortArrayListsByLength(partition12(al));
		default: 
			return Util.sortArrayListsByLength(partition12(al));
		}
	}
	/**
	 * creates all possible partitions with a size larger than 2
	 */
	private static ArrayList<ArrayList<Integer>> partition2(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				ArrayList<Integer> curPartition = new ArrayList<Integer>();
				curPartition.add(i);
				curPartition.add(j);
				partitions.add(curPartition);
			}
		}
		
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition3(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 3) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					ArrayList<Integer> curPartition = new ArrayList<Integer>();
					curPartition.add(i);
					curPartition.add(j);
					curPartition.add(k);
					partitions.add(curPartition);
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition4(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 4) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						ArrayList<Integer> curPartition = new ArrayList<Integer>();
						curPartition.add(i);
						curPartition.add(j);
						curPartition.add(k);
						curPartition.add(l);
						partitions.add(curPartition);
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition5(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 5) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							ArrayList<Integer> curPartition = new ArrayList<Integer>();
							curPartition.add(i);
							curPartition.add(j);
							curPartition.add(k);
							curPartition.add(l);
							curPartition.add(m);
							partitions.add(curPartition);
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition6(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 6) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								ArrayList<Integer> curPartition = new ArrayList<Integer>();
								curPartition.add(i);
								curPartition.add(j);
								curPartition.add(k);
								curPartition.add(l);
								curPartition.add(m);
								curPartition.add(n);
								partitions.add(curPartition);
							}
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition7(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 7) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								for(int o = n + 1; o < al.size(); o++) {
									ArrayList<Integer> curPartition = new ArrayList<Integer>();
									curPartition.add(i);
									curPartition.add(j);
									curPartition.add(k);
									curPartition.add(l);
									curPartition.add(m);
									curPartition.add(n);
									curPartition.add(o);
									partitions.add(curPartition);
								}
							}
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition8(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 8) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								for(int o = n + 1; o < al.size(); o++) {
									for(int p = o + 1; p < al.size(); p++) {
										ArrayList<Integer> curPartition = new ArrayList<Integer>();
										curPartition.add(i);
										curPartition.add(j);
										curPartition.add(k);
										curPartition.add(l);
										curPartition.add(m);
										curPartition.add(n);
										curPartition.add(o);
										curPartition.add(p);
										partitions.add(curPartition);
									}
								}
							}
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition9(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 9) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								for(int o = n + 1; o < al.size(); o++) {
									for(int p = o + 1; p < al.size(); p++) {
										for(int r = p + 1; r < al.size(); r++) {
											ArrayList<Integer> curPartition = new ArrayList<Integer>();
											curPartition.add(i);
											curPartition.add(j);
											curPartition.add(k);
											curPartition.add(l);
											curPartition.add(m);
											curPartition.add(n);
											curPartition.add(o);
											curPartition.add(p);
											curPartition.add(r);
											partitions.add(curPartition);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition10(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 10) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								for(int o = n + 1; o < al.size(); o++) {
									for(int p = o + 1; p < al.size(); p++) {
										for(int r = p + 1; r < al.size(); r++) {
											for(int s = r + 1; s < al.size(); s++) {
												ArrayList<Integer> curPartition = new ArrayList<Integer>();
												curPartition.add(i);
												curPartition.add(j);
												curPartition.add(k);
												curPartition.add(l);
												curPartition.add(m);
												curPartition.add(n);
												curPartition.add(o);
												curPartition.add(p);
												curPartition.add(r);
												curPartition.add(s);
												partitions.add(curPartition);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition11(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 11) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								for(int o = n + 1; o < al.size(); o++) {
									for(int p = o + 1; p < al.size(); p++) {
										for(int r = p + 1; r < al.size(); r++) {
											for(int s = r + 1; s < al.size(); s++) {
												for(int t = s + 1; t < al.size(); t++) {
													ArrayList<Integer> curPartition11 = new ArrayList<Integer>();
													curPartition11.add(i);
													curPartition11.add(j);
													curPartition11.add(k);
													curPartition11.add(l);
													curPartition11.add(m);
													curPartition11.add(n);
													curPartition11.add(o);
													curPartition11.add(p);
													curPartition11.add(r);
													curPartition11.add(s);
													curPartition11.add(t);
													partitions.add(curPartition11);
		
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return partitions;
	}
	private static ArrayList<ArrayList<Integer>> partition12(ArrayList<Integer> al) {
		ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();
		if(al.size() == 12) {
			partitions.add(Util.cloneArrayList(al));
			return partitions;
		}
		for(int i = 0; i < al.size(); i++) {
			for(int j = i + 1; j < al.size(); j++) {
				for(int k = j + 1; k < al.size(); k++) {
					for(int l = k + 1; l < al.size(); l++) {
						for(int m = l + 1; m < al.size(); m++) {
							for(int n = m + 1; n < al.size(); n++) {
								for(int o = n + 1; o < al.size(); o++) {
									for(int p = o + 1; p < al.size(); p++) {
										for(int r = p + 1; r < al.size(); r++) {
											for(int s = r + 1; s < al.size(); s++) {
												for(int t = s + 1; t < al.size(); t++) {
													for(int q = t + 1; q < al.size(); q++) {
														ArrayList<Integer> curPartition11 = new ArrayList<Integer>();
														curPartition11.add(i);
														curPartition11.add(j);
														curPartition11.add(k);
														curPartition11.add(l);
														curPartition11.add(m);
														curPartition11.add(n);
														curPartition11.add(o);
														curPartition11.add(p);
														curPartition11.add(r);
														curPartition11.add(s);
														curPartition11.add(t);
														curPartition11.add(q);
														partitions.add(curPartition11);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return partitions;
	}
}
