# 🅿️ Parking Lot System in Kotlin (LLD Interview Problem)

This repository contains a **Low-Level Design (LLD)** implementation of the **Parking Lot System** in **Kotlin**.  
It’s one of the most popular **LLD interview questions** and demonstrates **OOP design, SOLID principles, and concurrency handling**.

👉 Read the full article on Medium: [Parking Lot System in Kotlin: A Low-Level Design Interview Classic](https://medium.com/@nikhil.cse16/object-oriented-design-of-parking-lot-system-in-kotlin-c5ace3170c8b)  

---

## 📋 Problem Statement
Design a Parking Lot System that:
- Supports **multiple floors** and **multiple vehicle types** (Bike, Car, Truck).  
- Issues a **ticket** when a vehicle enters.  
- Calculates a **parking fee** when a vehicle exits.  
- Handles **concurrency** (e.g., two cars trying to grab the last spot).  

---

## 🧩 Features Implemented
- **OOP hierarchy** for Vehicles (Bike, Car, Truck).  
- ParkingLot → Floor → ParkingSpot relationships.  
- Ticket generation with entry & exit timestamps.  
- **FeeCalculator** with hourly rates.  
- Thread-safety using **locks** to prevent race conditions.  
- Example simulation of **concurrent vehicle entry**.  

---

## 🚀 How to Run

Clone this repo and run the Kotlin `main()` function:

```bash
git clone https://github.com/yourusername/parking-lot-lld-kotlin.git
cd parking-lot-lld-kotlin
