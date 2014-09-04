package exercise09;


	public class Billfold  extends Card
	{
		private Card card1 = null;
		private Card card2 = null;
		private int counter = 0;
		
		public Billfold()
		{ 
			card1 = null;
			card2 = null;
		}
		
		public void addCard(Card card)
		{ 
			if (card1 == null)
			{
				card1 = card;
			}
			else if (card2==null)
			{
				card2=card;
			}
		}
		
		public String formatCards()
		{
		  String name1 = card1.format();
		  String name2 = card2.format();
		  if ((name1 == null) && (name2==null))
		  {
			  return "[No cards to display]";
		  }
		  if (name2==null)
		  {
			  return "[" + name1 + "]";
		  }
		  else
		  {
			  return "[" + name1 + " | " + name2 + "]";
		  }
		}
		
		public int getExpiredCardCount()
		{
			if (card1.isExpired())
			{
				counter++;
			}
			if (card2.isExpired())
			{
				counter++;
			}
			return counter;
		}
		
	}