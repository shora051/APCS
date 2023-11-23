public class Team
{
    private Player[] players;
    
    public Team()
    {
        players = new Player[12];
    }
    public Team(int numPlayers)
    {
        players = new Player[numPlayers]; 
    }
    public void addPlayer(Player p, int index)
    {
        players[index] = p;;
    }
    public void printTeamStats()
    {
        for(int i = 0; i < players.length; i++)
        {
            System.out.print(players[i].getName() + "\t #" + players[i].getNumber() + "\t" + "average >>> " + players[i].getBattingAverageString());
            System.out.println();
        }
    }
}
