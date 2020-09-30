
# Proposed Tables
## Notes 
 - All nodes in the board game `Letters from White Chapel` are already numbered. Any time a node is referenced in these schemas, they will be using the board game determined NodeID.
 -When brainstorming we began with a table ``Nodes`` but quickly broke it into several tables to allow for normalization. For example, each node connects to many nodes, which would have broken 1st normal form.
 - To have `From_Node` and `To_Node` in the tables `Connections` and `Alleys` reference each other we will have to use the SQL constraint `deferrable`
 - Markdown does not have an option for underlining text, so table keys will be italicized
 - We have decided to make 2 tables for Node Connections instead of 1 table with a third column for connection type. We believe this will make development easier because (1) the tables will be smaller and (2) we will know explicitly which type of connection we are using by the table and not the column. 

## Connections
In Letters from Whitechapel, the player who plays Jack the Ripper moves unseen along a map of nodes. This table will contain the list of all nodes (To_Node) that can be reached in one step from any starting node (From_Node). It has a composite key because all nodes connect to at least two other nodes (there are no dead ends).


  - Connections(*From_Node*, *To_Node*)
    * Foreign Key: From_Node and To_Node reference Alleys.From_Node and Alleys.To_Node
    
## Alleys 
This table is similar to Connections, but instead lists all nodes that Jack can reach from any given node via a game mechanic called an “alley.” Using an alley, Jack can move to any node bordering any city block that his current node is adjacent to.

  - Alleys(*From_Node*, *To_Node*)
    * Foreign Key: From_Node and To_Node reference Connections
    
## Investigations
Jack’s opponents play as constables who can investigate a node to determine whether it has been visited by Jack at some point during the current "night" (AKA round). If so, it is said that that node contains a clue. This table keeps track of which nodes have been investigated, what turn they were investigated on, and whether that node contained a clue at that time. This table has a composite key because a node can be investigated multiple times per round and it is useful to distinguish each separate investigation by the turn on which it occurred.

  - Investigations(*Node*, *turn_investigated*, Has_Clue)
    * Foreign Key: Node and Turn_Investigated reference Jack_Moves
    * Foreign Key: Node and Turn_Investigated reference Possible_Locations
    
## Jack_Moves   
This table contains a row for each of Jack’s moves. Each row contains the turn number and whether he used a special movement ability. For example, Jack could use an alley to move many nodes away from his current node. Move_Type will be an enumerated list containing `Normal`, `Carriage`, and `Alley`. Destination Node will be the node that Jack actually ends his turn on. 

  - Jack_Moves(*Turn*, Move_Type, Destination_Node )
    * Foreign Key:  Turn reference Investigations
    * Foreign Key: Destination_Node references Connections.From_Node
    
## Possible_Locations 
This table is of direct interest to the constables. It contains a row for each of Jack’s possible node locations for each turn. For example, 
`SELECT * 
FROM Possbile_Locations 
WHERE Turn=2`
would tell you which nodes Jack could have reached by turn two.

  - Possible_Locations(*Turn*, *Node*)
    * Foreign Key: Node and Turn reference Jack_Moves
    * Foreign Key: Node and Turn reference Investigations
