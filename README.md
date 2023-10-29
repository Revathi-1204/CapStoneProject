- [ ] Requirements Specification
- [ ] Backend Code with Documentation
- [ ] Front end Code with Documentation
- [ ] ER Diagram/DataBase Model Visualization
- [ ] Test Coverage Reports





# Software Requirements Specification (SRS)

## 1. Introduction

### 1.1 Purpose

The purpose of this Software Requirements Specification (SRS) document is to provide a comprehensive description of the "Number Portability" project. This document outlines the functional and non-functional requirements, software architecture, and design considerations for building portals to handle mobile number portability between Donor Operator (Operator A) and Recipient Operator (Operator B).

### 1.2 Scope

The scope of this project encompasses the development of web-based portals and associated software components required to facilitate the mobile number portability process. The project includes the creation of stubs for mock API responses and the demonstration of various porting scenarios.

## 2. Functional Requirements

### 2.1. User Initiated Porting Request

#### 2.1.1 Description
- Users should be able to initiate a mobile number porting request by sending an SMS or calling a designated number.

#### 2.1.2 Acceptance Criteria

- The system should provide a short code for SMS initiation and a phone number for initiation calls.
- Users should receive a reply SMS containing a Unique Porting Code (UPC) within a reasonable time.
- The system must validate requests and generate UPCs accordingly.

### 2.2. UPC Validity and Management

#### 2.2.1 Description

- UPCs must have specific validity periods based on the subscriber's location. The system should track and manage UPC expiration.

#### 2.2.2 Acceptance Criteria

- UPCs must be generated with the correct validity period.
- The system should have a mechanism to manage and validate UPCs, rejecting those that have expired.

### 2.3. Subscriber Documentation and Information

#### 2.3.1 Description

- Subscribers should be able to submit porting requests to the Recipient Operator (RO) along with necessary documentation and information.

#### 2.3.2 Acceptance Criteria

- The system should specify required documentation, such as Customer Acquisition Forms (CAF), bill copies, or authorization letters.
- Users should be able to submit this information to the RO.

### 2.4. Request Forwarding to MNPSP

#### 2.4.1 Description

- The system should forward porting requests with necessary details to the Mobile Number Portability Service Provider (MNPSP).

#### 2.4.2 Acceptance Criteria

- Requests should be forwarded accurately, including mobile number, UPC, date of request, and Donor Operator information.
- The system should support integration with MNPSPs.

### 2.5. Verification and Rejection by MNPSP

#### 2.5.1 Description

- The MNPSP should verify porting requests and have the ability to reject them based on specific conditions.

#### 2.5.2 Acceptance Criteria

- The MNPSP must perform checks, including the verification of previous porting and pending requests.
- The system should be able to reject and communicate rejections to the respective operators.

### 2.6. DO Verification and Grounds for Rejection

#### 2.6.1 Description

- The system should support the Donor Operator (DO) in verifying and assessing specific grounds for rejection of porting requests.

#### 2.6.2 Acceptance Criteria

- The DO should be able to verify requests and apply grounds for rejection as needed.
- The system must receive and communicate clearance or rejection messages from the DO.

### 2.7. Communication of Rejection or Clearance

#### 2.7.1 Description

- The system should enable the MNPSP to communicate the clearance or rejection of porting requests to the Recipient Operator (RO) and the concerned subscriber.

#### 2.7.2 Acceptance Criteria

- The system should send clearances to RO and communicate rejections to the RO and the subscriber.
- Messages should include reasons for rejection.

### 2.8. Coordination and Mobile Activation

#### 2.8.1 Description

- The system should coordinate the activation and deactivation of mobile numbers between the Donor Operator (DO) and Recipient Operator (RO) at the predetermined date and time.

#### 2.8.2 Acceptance Criteria

- The system should coordinate with the DO and RO to ensure a seamless transition of the mobile number.
- Activation and deactivation must occur accurately within the specified time frame.

### 2.9. LRN Management and Broadcasting

#### 2.9.1 Description

- The system should manage Location Routing Numbers (LRN) and broadcast updated LRN and ported number information to all relevant operators.

#### 2.9.2 Acceptance Criteria

- The system should attach the correct LRN to the ported number.
- Updated LRN and ported number information must be broadcasted accurately to access providers and International Long Distance Operators.

## 3. Non-Functional Requirements

### 3.1. Performance

#### 3.1.1. Response Time

- The system should respond to user requests for initiating the porting process within 5 seconds.
- The porting process, including verification and communication, should be completed within 15 minutes.

#### 3.1.2. Scalability

- The system should be able to handle a minimum of 1,000 simultaneous porting requests without degradation in performance.
- It should scale horizontally to accommodate increased loads during peak hours.

### 3.2. Reliability

#### 3.2.1. Availability

- The system must have an uptime of at least 99.9% to ensure that porting requests can be initiated at any time.
- Scheduled maintenance should be communicated in advance, and a backup system should be in place to handle maintenance downtime.

#### 3.2.2. Fault Tolerance

- The system should be designed to recover from failures gracefully. In the event of a component failure, it should continue to process requests without data loss.
- Redundancy and failover mechanisms should be in place for critical system components.

### 3.3. Security

#### 3.3.1. Data Encryption

- All communication between the system and other involved parties (subscribers, operators, MNPSPs, etc.) should be encrypted using industry-standard encryption protocols (e.g., TLS).
- Subscriber data, including personal information and UPCs, should be stored securely with access controls and encryption.

#### 3.3.2. Authentication and Authorization

- The system must have robust authentication and authorization mechanisms to ensure that only authorized personnel can access and modify data.
- Subscriber identity and authorization checks should be performed rigorously during the porting process.

### 3.4. Compliance

#### 3.4.1. Regulatory Compliance

- The system must adhere to all telecom regulatory requirements as defined by the Telecom Regulatory Authority of India (TRAI) or any other relevant authority.
- It should be adaptable to changes in regulations and automatically enforce new compliance rules.

### 3.5. User Experience

#### 3.5.1. Usability

- The system's user interfaces (web portal, SMS interactions, etc.) should be intuitive and user-friendly.
- On-screen instructions should guide users through the porting process.

#### 3.5.2. Accessibility

- The system's user interfaces should be designed to be accessible to users with disabilities, following accessibility guidelines (e.g., WCAG).

### 3.6. Integration

#### 3.6.1. API Performance

- The system's APIs for integration with MNPSPs, DOs, and ROs should have low latency and high throughput to ensure efficient data exchange.
- API documentation should be available to support integration efforts.

### 3.7. Data Management

#### 3.7.1. Data Retention

- The system should define clear data retention policies and ensure compliance with data protection regulations.
- Subscriber data should be retained only for the necessary period and securely deleted when no longer needed.



## 4. Conclusion

This Software Requirements Specification (SRS) document outlines the functional and non-functional requirements for the "Number Portability" project. It serves as a reference for the development team to design, build, and test the mobile number portability system in compliance with user expectations and industry standards.

**Questions**

*1. MNP operator/MNPSP acts as a medium(coordinates) between the src op and target op , so how do we implement that in our app?*

*2. Communication between the SO and TO ? How?*
*3. Microservices Clarity on :*
- *Error on source system side - manual correction and resumption of workflow*
- *Error on target system side - cancellation of MNP and re-initiation of the full flow. Show how system data is cleaned up / corrected on both sides before initiating the second attempt of MNP*
- *What errors are expected to arise in this flow?*




