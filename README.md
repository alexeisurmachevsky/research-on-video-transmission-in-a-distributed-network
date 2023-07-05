# research-on-video-transmission-in-a-distributed-network
An application that creates a distributed network and transmits video in it

VIDEO TRANSMISSION IN A DISTRIBUTED NETWORK
Application of the distributed network video transmission method.

 
Introduction
Video transmission in a distributed network is a reliable and cost-effective way to deliver video content. This technology has high fault tolerance. In addition, it is much cheaper to maintain compared to transmission in a centralized network. The report discusses the principle of operation of a distributed video transmission network, its advantages and disadvantages.

I. Distributed network
A distributed network is a network in which computing power is distributed between nodes. Now such a network organization is used by BitTorrent, Bitcoin and Web 3.0.
The main advantages of a distributed network are: high fault tolerance, low cost of maintenance, anonymity (data is not stored on servers, thereby we can be sure that they will not get to third parties), confidentiality (data is transferred between fields this way we avoid data leaks from the server).
To organize video broadcasts to a small number of users, a peer-to-peer (peer-to-peer, decentralized) implementation of the network is proposed. In this case, this will not greatly affect fault tolerance and speed.

II. Video transmission using the UDP protocol
Video transmission over the Internet usually takes place over the UDP protocol, since it transmits data faster due to the fact that it does not wait for a response from the recipient. With UDP, the loss of a few frames is possible, which is not as significant as the delay.

III.	NAT Bypass
When building a distributed network, there is a problem of blocking traffic by NAT to prevent unauthorized access. To work around this problem, the UDP Hole Punching technique is used: two devices send a UDP packet to the server, thereby the NAT devices open the ports to receive data, and the server sends data about the IP address and the opened port of another device, after receiving this data, the devices exchange data without the participation of the server. The server is a link and does not participate in data transmission.

Several nodes are connected to one node, the number of which depends on the performance of the repeater device. To avoid disconnecting a node when a lower-order device is broken, a higher-order device receives information about lower-order devices and takes its place. Thus, the need for balancing the entire network is eliminated - there is an exchange of places between two nodes.
IV. The use of a distributed network for video transmission
The proposed approach was tested when transmitting video information using three nodes. The translator device sent a one-second video to the node, which transmitted the video to the next node. To obtain the exact delay time, the formula was used:
T = ∆D * N + Tr (1)
where T is the time, D is the delay, N is the order number in the network Tr is the recording time of a video fragment from the broadcasting side. A delay of less than one second was obtained in a network with three nodes. The disadvantage of such a network organization is an increase in the delay time when connecting new nodes. The graph of the delay dependence on the number of nodes is linear.

V. Conclusions
The organization of a distributed network for video broadcasting is proposed. The network is fault-tolerant and its maintenance is much easier and cheaper, unlike its centralized counterpart.
