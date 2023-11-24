# My Personal Project: PuppyPal

## Description

**PuppyPal** is an application for dog owners to keep track of their dogs' health, daily routines, or growth progress. 
It's like a digital diary and manager for the dog's well-being. Users can add the profile or identity of their dogs 
with name, weight, height and breed. PuppyPal helps records health property of the dog that allows users to monitor 
their dog's progress in health and many other aspects. This project is interesting for me because I personally love 
dogs, and I have some. Therefore, as a dog owner, I think it will make life so much easier to have an app that keeps
track of all our dog's needs and to ensure that our dogs are happy and healthy.

## User Stories
- As a user, I want to be able to add a profile for a dog in the application, including details like name, weight, 
  height, and breed.
- As a user, I want to be able to view the list of all my dogs’ profiles that I have added to the application.
-  As a user, I want to be able to remove a dog’s profile from the application when needed.
- As a user, I want to be able to add my dog’s health record such as medications, vaccinations, de-wormings,
  and etc with their date record.
- As a user, I want to be able to view my dog’s health records that I have added to my dogs.
- As a user, I want to be able to remove a health record from my dog’s health records.
- As a user, I want to be able to save my dogs and its health records when I want to do so.
- As a user, I want the option to load my dogs and its health records from a file.

## Instructions for Grader
- You can generate the first required action related to the user story "adding a profile for a dog" 
  by clicking the "Add" button on the main panel. \
  This will prompt you to enter details such as the dog's name, breed, weight, and height.
  
- You can generate the second required action related to the user story "view the list of all dog profiles" by 
  click the "View My Dogs" button on the main panel. A scrollable list of dogs with their details will be displayed.\
  You can click "BACK" button if you wish to go back to the main menu.
  
- You can generate the third required action related to the user story "remove a dog's profile" by 
  click the "Remove Dog" button on the main panel. \Enter the ID of the dog you want to remove in the prompted dialog.
    
- You can generate the forth required action related to the user story "adding a health record for a dog"
  by first make sure that you already have dog, if not you can add your dog profile first. \
  Afterward, you can click the "Add Health Records" button. \
  This will prompt you to enter health record details such as type, name, and date.

- You can generate the fifth required action related to the user story "view a dog's health records" by
  click the "View My Dog's Health Records" button on the main panel. \
  Enter the ID of the dog you want to view health records for in the prompted dialog. \
  A scrollable list of health records for the selected dog will be displayed.
  You can click "BACK" button if you wish to go back to the main menu.
  
- You can generate the sixth required action related to the user story "remove a health record from a dog" by
  click the "Remove Health Record" button on the main panel. \
  Enter the ID of the dog and the Health ID of the record you want to remove in the prompted dialog.

- You can locate the visual component (background image) by observing the top section of the main panel 
  when the application is running. A logo image is displayed here.

- To save the state of the application, click the "Save" button on the main panel.\
  This will serialize the list of dogs and their health records to a JSON file located at "./data/dogs.json."

- To reload the state of the application, click the "Load" button on the main panel. \
  This will deserialize the list of dogs and their health records from the JSON file located at "./data/dogs.json."

