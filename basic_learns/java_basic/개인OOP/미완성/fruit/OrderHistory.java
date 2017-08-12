package fruit;
public class OrderHistory { //주문 개수를 담는 클래스
	int[] orderCnt = new int[Fruit.SIZE]; //과일당 수문 수량을 담는 배열
	public OrderHistory(){} 	
	
	public void add(int idx){ //주문되면 개수 늘림
		orderCnt[idx]++;
	}	
	public void cancel(int idx){ //취소하면 개수 줄임
		if(orderCnt[idx]<1)
			return;
		orderCnt[idx]--;
	}	
	public int getCount(int idx){ //현재 주문된 수 반환
		return orderCnt[idx];
	}	
	public boolean isSaled(int idx){ //판매수량이 0보다 크면 true ,아니면 false
		return orderCnt[idx]!=0;
	}		
	public synchronized void addTableOrder(OrderHistory tableOrder){ //table의 OrderHistory객체를 받아서 가게의 전체 수량에 더해준다
		for(int i=0;i<orderCnt.length;i++){
			orderCnt[i]+=(tableOrder.getCount(i));
		}
	}		
	public String getSalesInfo(int idx){ //판매 정보를 문자열로 반환
		return Fruit.NAME[idx]+"(수량:"+orderCnt[idx]+") 합 : "+(Fruit.PRICE[idx]*orderCnt[idx])+"원";
	}	
	public int getTotal(){ //전체 판매 금액을 구한다
		int total = 0;
		for(int i=0;i<orderCnt.length;i++){
			total+=(orderCnt[i]*Fruit.PRICE[i]);
		}
		return total;
	}	
}
