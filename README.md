# Primitive Audio Sequencer

**by Chicory**



## Project Proposal

#### Background

As I have been passionate about music production and composition since middle school, 
I have always wanted to create software tools that allow musicians to write and produce music.
I found this project as a perfect opportunity to kickstart my goals,
as I would be able to write a simplistic audio-related application with guidance and knowledge from this course.


#### Overview

The Primitive Audio Sequencer will be an application that will allow users to load and play back audio clips.
The main interface will consist of a grid,
where each row represents a track and each column represents a unit of time.
Each imported sound will have its own track and users will be able to indicate where in time their sounds will play.
The included mixer will allow users to solo, mute, and control the gain of each track.


#### Target Audience

This application will be ideal for users who wish to begin their first steps as a music producer/composer.
As the application will be very basic, it will be very easy for anyone to pick up and use.



## User Stories

#### Core Features

- As a user, I want to be able to import audio files from local storage and add them to my project 
- As a user, I want to be able to indicate when (in musical time) my sounds will play
- As a user, I want to be able to play back my project
- As a user, I want to be able to remove sounds/tracks from the project

#### Quality-of-Life Features

- As a user, I want to be able to mute tracks
- As a user, I want to be able to rename my project
- As a user, I want to be able to extend my project
- As a user, I want to be able to change the tempo/BPM of my project

#### Data Persistence Features

- As a user, I want to be able to save my progress/project to a file
- As a user, I want to be able to load my progress/project from a selected file



## Instructions for Grader

#### Add X to Y

- You can add a track by selecting `Project > Add` in the menu bar. From there, select a sound file and provide your track with a name.

#### Required Events

- You can toggle notes on and off with the sequence of buttons at the right side of each track.
- You can remove a track by clicking on the button with the trash icon at the left side of each track.

#### Visual and Audio Components

- You can see the trash and mute icons on the buttons at the left side of each track.
- You can play back your project by selecting `File > Play` in the menu bar.

#### Persistence

- You can rename your project by selecting `Project > Rename` in the menu bar.
- You can save your project by selecting `File > Save` in the menu bar. The project will be saved as `<Project Name>.json`.
- You can load a saved project by selecting `File > Load` in the menu bar. From there select a JSON file that you wish to load.



## Phase 4: Task 2

```Wed Aug 10 11:26:30 PDT 2022
Track 'Kick' with file 'kick.wav' added to project.

Wed Aug 10 11:26:44 PDT 2022
Toggled note of track 'Kick' at index 0.

Wed Aug 10 11:26:45 PDT 2022
Toggled note of track 'Kick' at index 4.

Wed Aug 10 11:26:47 PDT 2022
Toggled note of track 'Kick' at index 8.

Wed Aug 10 11:26:49 PDT 2022
Toggled note of track 'Kick' at index 12.

Wed Aug 10 11:26:51 PDT 2022
Toggled note of track 'Kick' at index 16.

Wed Aug 10 11:27:02 PDT 2022
Track 'Snare' with file 'snare.wav' added to project.

Wed Aug 10 11:27:04 PDT 2022
Track 'Kick' removed.

Wed Aug 10 11:27:07 PDT 2022
Toggled note of track 'Snare' at index 4.

Wed Aug 10 11:27:14 PDT 2022
Toggled note of track 'Snare' at index 12.
```



## Phase 4: Task 3

Unfortunately, I found phase 3 a lot more difficult and time-consuming than I had anticipated,
leading to code confusion and, ultimately, poor code being written.

Therefore, I ended up giving all panels and buttons ended a reference to `GraphicalInterface`.
I had these UI elements use this reference in their update methods to reference the current project.
However, this is not ideal since it introduces strong coupling.
If I had more time to refactor and clean up my project, I would use one of two methods to fix this problem.
One solution is to use Singleton design, where I would have one instance of `Project` and `GraphicalInterface`
as which can be accessed from any class.
The other solution is to implement an observer pattern where the panels and buttons are updated whenever Project is changed.

Additionally, due to a lack of time, many of the constants were placed in the `GraphicalInterface` classes for convenience.
I would organize and move these constants the correct classes where they are actually needed.

Furthermore, most of my constructors ended up being really lengthy and disorganized,
with most or all of the initialization happening in the constructor.
If I had more time, I would have preferred to extract a lot of this initialization code into their respective methods,
making the code cleaner and more legible.