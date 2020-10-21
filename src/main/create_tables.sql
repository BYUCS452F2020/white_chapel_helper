CREATE TABLE Connections(
  from_node NUMBER NOT NULL,
  to_node NUMBER NOT NULL,
  connection_type TEXT NOT NULL,
	PRIMARY KEY (from_node, to_node)
);

CREATE table Jack_Moves(
	turn NUMBER NOT NULL,
	move_type TEXT NOT NULL,
	destination_node NUMBER NOT NULL,
	PRIMARY KEY (turn)
);

CREATE TABLE Investigations(
	node NUMBER NOT NULL,
	turn_investigated NUMBER  NOT NULL,
	has_clue BOOLEAN NOT NULL,
-- 	  I'm not sure if Booleans exist...^^
	PRIMARY KEY (node, turn_investigated)
);

CREATE TABLE Possible_Locations(
	turn NUMBER,
	node NUMBER,
	PRIMARY KEY (turn, node),
	FOREIGN KEY (turn) references Jack_Moves(turn)
);
