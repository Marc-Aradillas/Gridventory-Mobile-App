<p align="center">
<img width="289" height="289" alt="logo" src="https://github.com/user-attachments/assets/13de7d60-4461-4308-8ada-c8a5227cf137" />
</p>

# Gridventory - Inventory Management App

<!--[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)'-->
[![Android](https://img.shields.io/badge/Platform-Android-brightgreen)](https://developer.android.com)

**Course**: CS 360 - Mobile Architecture and Programming  
**Project**: Project Three  

---

## Project Overview
**Gridventory** is an Android mobile app designed to streamline inventory management for small businesses and individuals. Its primary goal is to simplify tracking, updating, and managing inventory in real-time. The app ensures users can efficiently monitor stock levels and receive alerts when supplies are low.  

### User Needs Addressed
- **Problem**: Users required an affordable, easy-to-use solution for managing inventory without complex software.  
- **Solution**: Gridventory offers an intuitive dashboard for adding, deleting, and updating items. It also sends SMS notifications when stock levels reach zero, enabling proactive restocking.  

---

## Features & User Interface
- **Login Screen**: Secure authentication with account creation options.  
- **Inventory Dashboard**: Displays current stock with add, edit, and delete options.  
- **SMS Notifications**: Alerts for low-stock items, configurable by the user.  
- **Responsive Design**: Optimized layout for multiple screen sizes.  

**User-Centered Design Approach**:  
- **Minimalist Interface**: Clear, easy-to-use interface with labeled buttons and input fields.  
- **Error Prevention**: Input validation and prompts for accurate data entry.  
- **Feedback Loops**: Toast messages confirm successful operations.  

---

## Tech Stack & Development
- **Language**: Java  
- **IDE**: Android Studio  
- **Database**: SQLite for local persistent storage  
- **UI Components**: RecyclerView, Buttons, EditText fields  
- **Event Handling**: `OnClickListener` callbacks for interactive UI  

**Development Approach**:  
- Modular activities for each major feature (Login, Inventory, SMS) to enhance maintainability.  
- Dynamic RecyclerView integration for real-time inventory updates.  
- Permission handling for SMS notifications using `onRequestPermissionsResult`.  

---

## Testing & Validation
- **Unit Testing**: Individual feature tests to ensure functionality.  
- **Real-Device Testing**: Verified compatibility across emulators and physical devices.  
- **Error Handling**: Managed edge cases such as empty inputs or invalid login attempts.  

**Key Takeaway**: Iterative testing revealed UI inconsistencies and performance issues, enabling improvements.  

---

## Challenges & Solutions
- **SMS Permission Integration**: Managing runtime permissions and fallback logic.  
  - **Solution**: Implemented `onRequestPermissionsResult` with appropriate user feedback.  

---

## Achievements
- Secure user authentication preventing unauthorized access.  
- Cohesive, responsive UI design enhancing user experience.  
- Full CRUD functionality for inventory management.  

---

## Next Steps
- **Launch Consideration**: Refine and publish on Google Play Store with features like cloud sync and barcode scanning.  
- **Portfolio Use**: This project demonstrates mobile development skills for potential employers.  

---

## Screenshots

<p align="center">
 <img width="309" height="550" alt="image" src="https://github.com/user-attachments/assets/e0764ad1-989f-43d4-ad50-62bc0d3960f9" /><br>
 <em>Activity Login</em>
</p>

<p align="center">
 <img width="308" height="548" alt="image" src="https://github.com/user-attachments/assets/a3cbbab6-cc55-46ea-b97e-1b2d14d0d8b4" /><br>
 <em>Inventory Dashboard</em>
</p>

<p align="center">
 <img width="314" height="597" alt="image" src="https://github.com/user-attachments/assets/e54a228b-c9f9-42f4-bd68-1d0e1acedbc1" /><br>
 <em> SMS Notification Request</em>
</p>
