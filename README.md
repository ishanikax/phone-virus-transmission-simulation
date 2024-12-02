# 📱Phone-Virus-Transmission-Simulation
![Maternal (1)](https://github.com/user-attachments/assets/d59b99d7-ed14-41b8-b7b0-69236c7c3e39)

GUI mobile phone virus transmission simulation in Java.

In this mobile phone virus transmission simulation, a virus begins its journey in a randomly selected phone and quickly spreads to others within a 20-pixel range. Once infected, a phone's only chance of survival is to make it to the repair shop, where it can be fixed. However, the repair shop can only handle one phone at a time, creating a critical race against the clock. If an infected phone isn't repaired within 500 frames, it will be removed from the simulation, simulating its "death." The simulation relies on a carefully designed "synchronized" algorithm to manage this urgent repair process, ensuring a dynamic and engaging experience as you observe the virus's relentless spread and the phones' struggle to survive.

<h2>Summary</h2>
Successfully designed and implemented a comprehensive Virus Transmission Simulation featuring mobile phone-like icons running as threads. This simulation showcases intricate behavior such as random movement, virus spread, and synchronized repair mechanics, all brought together within a custom-designed GUI.

<h3>Features Delivered:</h3>

<h4>1. Custom GUI Design</h4>
<p>✦ Created a visually engaging simulation environment with vibrant representations of phones, a repair shop, and real-time status changes.</p>

<h4>2. Dynamic Phone Behavior</h4>
<p>✦ Phones move randomly across the simulation space, each functioning as an independent thread.</p>
<p>✦ Pressing the up arrow key dynamically adds new phones to the simulation.</p>

<h4>3. Virus Transmission</h4>
<p>✦ Introduced a "virus" that infects a random phone when the 'v' key is pressed.</p>
<p>✦ The virus spreads to phones within a 20-pixel range, simulating real-world proximity-based transmission.</p>

<h4>4. Life Cycle Management</h4>
<p>✦ Each infected phone has a lifespan countdown (500 frames).</p>
<p>✦ If not repaired within the countdown, the phone is removed from the simulation.</p>

<h4>5. Color-Coded Phone States</h4>
<p>✦ Used distinct colors to visually differentiate:</p>
<li>Healthy phones</li> 
<li>Infected phones </li> 
<li>Phones moving to the repair shop</li> 


<h4>6. Repair Shop & Synchronization</h4>
<p>✦ Developed a synchronized algorithm ensuring only one phone is repaired at a time.</p>
<p>✦ Guaranteed thread safety with no race conditions by selecting a shared object for synchronization.</p>

<h3>Technical Highlights:</h3>
<p>🌻 Leveraged multithreading to enable each phone to function independently.</p>
<p>🌻 Designed a synchronized block to manage repair shop access effectively.</p>
<p>🌻 Supported dynamic phone addition and real-time virus behavior with keyboard input handling.</p>
<p>🌻 Incorporated either a linked list or an array for efficient phone storage and management.</p>



<h3>Impact</h3>
This simulation provides a compelling visual and interactive experience while demonstrating advanced concepts like threading, synchronization, and real-time system updates. It highlights the practical application of multithreaded design and synchronized access in a controlled environment.

<h2>How-To</h2>
All code cam be found under "Phone_Virus_Transmission_Simulation/src/Question_2"

To showcase simulation, download the package and run "VirusSimulation.java"



Click link below and select "raw file" to download a video of the simulation in action!

[Watch the Video Demonstration](PVSDemoVideo.mp4)




