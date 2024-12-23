# Gridventory - Inventory Management App  
**Course**: CS 360 - Mobile Architect and Programming  
**Project**: Project Three  

## Project Overview  
Gridventory is an Android mobile app designed to streamline inventory management for small businesses and individuals. Its primary goal is to simplify tracking, updating, and managing inventory in real-time. The app ensures users can efficiently monitor stock levels and receive alerts when supplies are low.  

### User Needs Addressed  
- **Problem**: Users needed an affordable, easy-to-use app to manage inventory without complex software.  
- **Solution**: Gridventory offers an intuitive dashboard with add, delete, and update features. The app also sends SMS notifications when stock levels reach zero, ensuring proactive restocking.  

---

## UI Screens and Features  
- **Login Screen**: Secure user authentication with options to create an account.  
- **Inventory Dashboard**: Displays current stock with options to add, edit, or delete items.  
- **SMS Notifications**: Users can enable or disable SMS alerts for low-stock items.  
- **Responsive Design**: The layout is optimized for various screen sizes, ensuring accessibility across devices.  

**User-Centered Design Approach**:  
- **Minimalist Interface**: Ensures ease of use with clearly labeled buttons and input fields.  
- **Error Prevention**: Input validation and error prompts guide users in entering accurate information.  
- **Feedback Loops**: Toast messages confirm successful operations, providing instant user feedback.  

---

## Coding and Development Approach  
- **Modular Development**: Each feature (login, inventory, SMS) was developed as a separate activity to ensure maintainability.  
- **RecyclerView Integration**: Displays inventory data dynamically and allows for seamless item addition and deletion.  
- **Database Handling**: SQLite was used for persistent storage to ensure inventory data is saved locally.  
- **OnClick Listeners**: Button interactions were handled using `OnClickListener` callbacks, making the app responsive and interactive.  

**Future Application**: These techniques can be adapted for any app requiring database management and dynamic data displays.  

---

## Testing and Validation  
- **Unit Testing**: Each feature was tested individually to ensure proper functionality.  
- **Real-Device Testing**: The app was tested on emulators and physical devices to ensure compatibility across screen sizes and Android versions.  
- **Error Handling**: Edge cases such as empty input fields and invalid login attempts were handled gracefully.  

**Importance of Testing**: This process revealed UI inconsistencies and performance bottlenecks, allowing for iterative improvements.  

---

## Challenges and Innovations  
- **SMS Permission Handling**: One major challenge was integrating SMS notifications, requiring dynamic permission requests and user feedback.  
- **Solution**: Implemented `onRequestPermissionsResult` to handle runtime permissions and fallback methods if permissions were denied.  

---

## Successes and Key Highlights  
- **User Authentication**: Successfully developed a secure login system that prevents unauthorized access.  
- **UI Consistency**: Achieved a cohesive and responsive design that enhances the user experience.  
- **Dynamic Inventory Management**: Fully functional CRUD (Create, Read, Update, Delete) operations for inventory items.  

---

## Next Steps  
- **Launch Consideration**: I plan to refine and publish the app on the Google Play Store, potentially incorporating additional features such as cloud sync and barcode scanning.  
- **Resume Integration**: This project will serve as a key artifact in my portfolio to demonstrate mobile app development skills to potential employers.  

---
