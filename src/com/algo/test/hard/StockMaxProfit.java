package com.algo.test.hard;

/**
 * 股票买卖的最佳时机。该问题有很多变种。
 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 设计一个算法来找到最大的利润。你最多可以完成 k 笔交易。
 * 
 * 但要注意，你不可以同时参与多比交易。也就是说，你在下一笔买入之前，比如先把持有的卖掉。
 * 
 * 例如：给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6。（4买入，6卖掉；1买入，5卖掉）
 * 
 * @author guozhen@proudsmart.com
 *
 */
public class StockMaxProfit {

	
	
	/**
	 * 股票买卖的最佳时机（第一题）。
	 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
	 * 设计一个算法来找到最大的利润。你最多可以完成 1笔交易。
	 * 
	 * 但要注意，你不可以同时参与多比交易。也就是说，你在下一笔买入之前，比如先把持有的卖掉。
	 * 
	 * 我们定义两个数组（buy和sell）分别用来表示某天如果处于买入状态能花费的最少的钱，和卖出状态能赚的最多的钱
	 * 如果第i天处于买入状态，则要么前几天已经买了，今天没有卖；要么前几天没有买，今天去买。状态转移为：buy[i] = min(buy[i-1], prices[i])
	 * 如果第i天处于卖出状态，则要么前几天已经卖了，今天没得卖；要么前几天没有卖，今天去卖。则今天卖了最大的收获是：sell[i] = max(sell[i-1], sell[i]-buy[i-1])
	 * 
	 * 此外还要定义初始状态：
	 * buy[0] = prices[0]
	 * sell[0] = 0
	 * 
	 * @author guozhen@proudsmart.com
	 *
	 */
	public static int maxProfitWith1Deal(int[] prices){
		int[] buy = new int[prices.length];
		int[] sell = new int[prices.length];
		
		buy[0] = prices[0];
		sell[0] = 0;
		
		for(int i = 1; i < prices.length; i++){
			buy[i] = Math.min(buy[i-1], prices[i]);
			sell[i] = Math.max(sell[i-1], prices[i]-buy[i-1]);
		}
		
		for(int i = 0; i < prices.length; i++){
			System.out.print(buy[i]);
		}
		
		System.out.println("\n--------------");
		
		for(int i = 0; i < prices.length; i++){
			System.out.print(sell[i]);
		}
		
		return sell[prices.length-1];
	}

	/**
	 * 股票买卖的最佳时机（第二题）。
	 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
	 * 设计一个算法来找到最大的利润。你可以任意执行若干次交易。
	 * 
	 * 但要注意，你不可以同时参与多比交易。也就是说，你在下一笔买入之前，比如先把持有的卖掉。
	 * 
	 * 在这种情形下，最简单的思路就是：只要赚钱，就可以去买卖。
	 * @author guozhen@proudsmart.com
	 *
	 */
	public static int maxProfitWithInfiniteDeal(int[] prices){
		int maxProf = 0;
		for(int i = 1; i < prices.length;i++){
			if(prices[i] > prices[i-1])
				maxProf += prices[i] - prices[i-1];
		}
		
		return maxProf;
		
	}
	
	/**
	 * 股票买卖的最佳时机（第三题）。
	 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
	 * 设计一个算法来找到最大的利润。你最多可以完成 2 笔交易。
	 * 
	 * 但要注意，你不可以同时参与多比交易。也就是说，你在下一笔买入之前，比如先把持有的卖掉。
	 * 
	 * 例如：给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6。（4买入，6卖掉；1买入，5卖掉）
	 * 
	 * 这里没有提到是否当天卖了当天再买，这种方式本身也没有意义。
	 * 
	 * 解决该问题可以使用动态规划的方式来求解。
	 * 定义四个数组：
	 * firstBuy
	 * firstSell
	 * secondBuy
	 * secondSell
	 * 
	 * firstBuy和secondBuy代表当天是第一次买和第二次买所能花费的钱的最小值
	 * firstSell和secondSell代表当天是第一次卖和第二次卖所能赚到的钱的最大值。
	 * 
	 * 如果第i天为首次买入的状态，则要么是第i天之前就买了，第i天没有卖，此时该值等于firstBuy[i-1]；要么是第i天当天买了，此时该值等于prices[i]。
	 * 如果第i天是第一次卖的状态，则要么是之前就卖掉了，第i天没有买，此时该值等于firstSell[i-1]；要么第i天当天卖了，此时该值等于prices[i]-firstBuy[i-1]
	 * 
	 * 如果第i天为第二次买入的状态，则要么是前几天已经卖过第一次并买入第二次了，第i天没有买；此时该值等于secondBuy[i-1]；要么是前几天已经卖过第一次，今天去买；则该值是：prices[i]-firstSell[i-1]
	 * 如果第i天为第二次卖出的状态，则要么是前几天已经卖过第二次了，今天没有操作，此时该值等于secondSell[i-1]；要么是前几天买过第二次没有卖，今天去卖第二次；此时该值是：prices[i]-secondBuy[i-1]
	 * 
	 * 此外，还需要定义初始值：
	 * firstBuy[0] = prices[0]
	 * firstSell[0] = 0;
	 * secondBuy[0] = prices[0]
	 * secondSell[0] = 0;
	 * 
	 * @author guozhen@proudsmart.com
	 *
	 */
	public static int maxProfitWith2Deal(int[] prices){
		int[] firstBuy = new int[prices.length],  firstSell = new int[prices.length], secondBuy = new int[prices.length], secondSell = new int[prices.length];
		firstBuy[0] = secondBuy[0] = prices[0];
		
		for(int i = 1; i < prices.length; i++){
			firstBuy[i] = Math.min(firstBuy[i-1], prices[i]);
			firstSell[i] = Math.max(firstSell[i-1], prices[i]-firstBuy[i-1]);
			secondBuy[i] = Math.min(secondBuy[i-1], prices[i] - firstSell[i-1]);
			secondSell[i] = Math.max(secondSell[i-1], prices[i] - secondBuy[i-1]);
		}
		
		for(int i = 0; i < prices.length; i++){
			System.out.print(firstBuy[i]);
		}
		
		System.out.println("\n--------------");
		
		for(int i = 0; i < prices.length; i++){
			System.out.print(firstSell[i]);
		}
		
		System.out.println("\n--------------");
		
		for(int i = 0; i < prices.length; i++){
			System.out.print(secondBuy[i]);
		}
		
		System.out.println("\n--------------");
		
		for(int i = 0; i < prices.length; i++){
			System.out.print(secondSell[i]);
		}
		
		System.out.println("\n--------------");
		
		
		return secondSell[prices.length-1];
	}
	
	/**
	 * 股票买卖的最佳时机（第四题）。
	 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
	 * 设计一个算法来找到最大的利润。你最多可以完成 k 笔交易。
	 * 
	 * 如果k大于prices.length，则基本代表了可以进行无限次数的交易，就变成了第二题。
	 * 我们这里仅考虑k小于prices.length的情形。
	 * 
	 * 但要注意，你不可以同时参与多比交易。也就是说，你在下一笔买入之前，比如先把持有的卖掉。
	 * 
	 * 例如：给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6。（4买入，6卖掉；1买入，5卖掉）
	 * 此方法为该题使用动归解题的第一种思路：使用二维数组来解体。
	 * 
	 * 定义两个二维数组int[prices.length+1][k+1]为buy和sell。
	 * 
	 * 其中buy[i][j]代表第i+1天处于第j+1次买入状态时，最大的盈利。则buy[i][j]要么是第i天执行了第j次买入，
	 * 此时该值为：sell[i-1][j-1]-prices[i]；
	 * 或者在第i天之前就完成了第j次买入且没有卖出，此时该值为buy[i-1][j]
	 * 
	 * sell[i][j]代表第i天处于第j次卖出的状态时，最大的盈利。同样的，sell[i][j]要么是第i天执行了第j次卖出，此时，该值等于：buy[i-1][j]+prices[i]
	 * 或者在第i天之前就完成了第j次卖出，此时该值为sell[i-1][j]
	 * 
	 * 初始状态的定义为：
	 * 第一天不论处于第几次买入的状态，买入后收益都是-prices[0]
	 * buy[1][1-prices.length+1] = -prices[0]
	 * 
	 * 而第一天不论处于第几次卖出的状态，卖出后收益都是0（当天买了又卖了，没有收益）
	 * sell[1][1-prices.length+1] = 0
	 * 
	 * 
	 */
	public static int maxProfitWithKDeal2D(int[] prices, int times){
		int[][] buy = new int[prices.length+1][times+1];
		int[][] sell = new int[prices.length+1][times+1];
		for(int i = 1; i < buy[0].length; i++){
			//第一天不论处于第几次买入状态，都亏了第一天的价格的钱数
			buy[1][i] = -prices[0];
		}
		
		for(int i = 2; i <= prices.length; i++){
			for(int j = 1; j <= times; j++){
				buy[i][j] = Math.max(sell[i-1][j-1]-prices[i-1], buy[i-1][j]);
				sell[i][j] = Math.max(buy[i-1][j]+prices[i-1], sell[i-1][j]);
			}
		}
		
		return sell[prices.length][times];
	}
	
	/**
	 * 股票买卖的最佳时机（第四题）。
	 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
	 * 设计一个算法来找到最大的利润。你最多可以完成 k 笔交易。
	 * 
	 * 如果k大于prices.length，则基本代表了可以进行无限次数的交易，就变成了第二题。
	 * 我们这里仅考虑k小于prices.length的情形。
	 * 
	 * 但要注意，你不可以同时参与多比交易。也就是说，你在下一笔买入之前，比如先把持有的卖掉。
	 * 
	 * 例如：给定价格 = [4,4,6,1,1,4,2,5], 且 k = 2, 返回 6。（4买入，6卖掉；1买入，5卖掉）
	 * 
	 * 这里没有提到是否当天卖了当天再买，这种方式本身也没有意义。
	 * 
	 * 解决该问题可以使用动态规划的方式来求解。
	 * 
	 * 参考最多两次交易的实现，我们发现第i次的值只跟i-1的状态相关。我们无法使用k*2个数组来解，
	 * 那么我们可以考虑用sell[]和buy[]两个长度为k的数组，记录第k次交易的状态。
	 * buy[k]代表第k次买入状态时，最大的盈利。
	 * sell[k]代表第k次卖出状态时，最大的盈利。
	 * 
	 * 假设buy[k]代表第i天处于第k次买入的状态，则要么在前i-1天就已经进行了第k次买入，此时的值仍然是buy[k]；或者在i天当天进行了第k次买入，此时值为sell[k-1]-prices[i]
	 * 所以buy[k] = max(buy[k], sell[k-1]-prices[i])
	 * 
	 * 假设sell[k]代表第i天处于第k次卖出的状态；则要么前i-1天已经完成了这状态，此时该值仍然是sell[k]；或者在第i天当天进行了第k次卖出，此时值为:buy[k]+prices[i]
	 * 所以，sell[k] = max(sell[k], buy[k]+prices[i])
	 * 
	 * 在这里，第i天的buy[k]需要用到前一天是sell[k-1]。假如我们从k=0开始遍历，
	 * 
	 * http://blog.csdn.net/danielrichard/article/details/75091410
	 * @author guozhen@proudsmart.com
	 *
	 */
	public static int maxProfitWithKDeal1D(int[] prices, int times){
		int length = prices.length;
		//我们最多只需要length/2次交易就足够了；例如：prices长度是10，则我们最多只需要五次买入和五次卖出，就可以参与每一天的交易了。
		if(times > length/2)
			times = length/2;
		
		int[] buy = new int[times];
		int[] sell = new int[times];
		for(int i = 0 ; i < times; i++){
			buy[i] = -prices[0];
			sell[i] = 0;
		}
		
		//数组之所以能从二维压缩到一维，关键在于：每一天只用到上一天的状态，所以用一个一维数组记录下第一天的状态，计算第二天时，将第一天的数据覆盖掉即可。
		//而在计算第三天时，只需要第二天的数据即可。如此循环，就可以求出最后一天。
		for(int i = 1; i < length; i++){
			buy[0] = Math.max(buy[0], -prices[i]);
			sell[0] = Math.max(sell[0], buy[0] + prices[i]);
			
			//由于第j天的buy要用到前一天的sell[j-1](也就是上次循环的sell[j-1]），所以这里从后向前循环。
			//如果从前向后循环，则这时第j天的buy用到的时候取出的sell[j-1]就是当天的sell[j-1]了，而不是前一天也就是上次循环留下的sell[j-1]了。
			for(int j = times-1; j > 0; j--){
				buy[j] = Math.max(buy[j], sell[j-1]-prices[i]);
				sell[j] = Math.max(buy[j]+prices[i], sell[j]);
			}
		}
		return sell[times-1];
	}
	
	
	public static int maxProf1DCopy(int[] prices, int times){
		times = times > prices.length/2 ? prices.length/2 : times;
		//buy[i]表示在第n天处于第i+1次买入时，最大的盈利；n在这里并没有表示，是在循环中表示的
		int[] buy = new int[times];
		//sell[i]表示在第n天处于第i+1次卖出时，最大的盈利；同样的，n也是在循环中使用
		int[] sell = new int[times];
		
		//初始化第一天的buy和sell。第一天，不论买入多少次，其最大盈利都是-prices[0]
		//而第一天，不论卖出多少次，盈利都是0
		for(int i = 0; i < times; i++){
			buy[i] = -prices[0];
		}
		
		for(int i = 1; i < prices.length; i++){
			//如果进入了第i天，仍然是第一次买入的状态，则此时第一次买入需要花的最少的钱，就看第i天最少的钱是否小于当前的钱了。
			buy[0] = Math.max(buy[0], -prices[i]);
			//如果进入了第i天仍然是第一次卖出的状态，看看怎么卖效果最好。
			sell[0] = Math.max(sell[0], buy[0] + prices[i]);
			
			for(int j = times-1; j > 0; j--){
				buy[j] = Math.max(buy[j], sell[j-1]-prices[i]);
				sell[j] = Math.max(buy[j]+prices[i], sell[j]);
			}
			
			System.out.println("buy:");
			for(int j = 0; j < times; j++){
				System.out.print(buy[j]);
			}
			System.out.println("");
			
			System.out.println("sell:");
			for(int j = 0; j < times; j++){
				System.out.print(sell[j]);
			}
			System.out.println("");
			
		}
		
		return sell[times-1];
		
	}
	
	public static void main(String[] args) {
		int prices[] = {4,4,6,1,1,4,2,5};
		int maxProf = maxProfitWithKDeal1D(prices, 2);
		System.out.println("\n"+maxProf);
		
		maxProf = maxProf1DCopy(prices, 2);
		System.out.println("\n"+maxProf);
		
//		maxProf = maxProfitWithKDeal1D(prices, 3);
//		System.out.println("\n"+maxProf);
//		
//		maxProf = maxProf1DCopy(prices, 3);
//		System.out.println("\n"+maxProf);
//		
	}
}
