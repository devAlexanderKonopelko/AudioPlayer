# AudioPlayer
Simple audio player.

### Features
- Show songs list.
- Refresh songs list by vertical swipe.
- Make song favorite by clicking on Favorite button. Note: only one favorite song can be set.
- Show song details.
- Playing song.
- Controlling song play: play forward, play backward, move to exact part of the song using seek indicator, pause.
- Make song favorite on Details screen.
- Rate song from 0 to 5.

---

### Test Coverage
- SongViewModelTest

---

### Further Improvements
  #### Features
  - Remove Play icon from song cover image (API side)
  - Add separate Play button over song cover
  - Play song from the main list by cover click
  - Add songs categories
  - Add notification bar UI for playing music in background
  - Add share show button to song details screen
  #### Refactoring
  - Moving non-UI logic to view models
  - Adding use-cases and interactors for proceeding non-UI logic
  - UI changes for api error cases
  - Increase test coverage
