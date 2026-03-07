## Assumptions
- Only active games should be posted on the score board. If the game is completed is removed from the score board.
- The score board should be sorted by total score in descending order. If two or more games have the same total score, they should be sorted by the most recently started game first.
- If the team is already playing a game, it should not be allowed to start another game with this team until the current game is completed.
- The score cannot be negative.

## Implementation
- GameService: This service will handle the logic for starting a game, updating the score, and finishing a game. It will maintain a list of active games and their scores.
- In my solution I used LinkedHashSet to store the active games:
  - to be sure, that we don't have duplicates, 
  - to maintain the order of games added to the collection, 
  - because it allows to add and remove from the collection by object faster O(1).
- 
