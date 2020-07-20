# Blockchain - Ajtai Hash Function

Blockchain Simulation using Lattice-Based Ajtai Hash Function

## Introduction

In this project, which is part of a Bachelor's thesis in Computer Science on the theme of *post-quantum cryptography*,
it is proposed to use a lattice-based hash function, better known as the *Ajtai hash function*,
instead of the standard SHA-256, in the implementation of a basic blockchain.

## Description

This blockchain simulation is composed of the following 5 Java classes :

- *Environment.java* (Main class)

  Main class in which the Ajtai key is generated 

- *Block.java*

  The necessary fields for the complete representation of a block include the following elements :
  
    - Hash of the previous block
    - Merkle root (computed from a set of transactions)
    - Nonce
    - Hash of the block (computed as the Ajtai hash function of the first three elements)
    - Timestamp (date + time)
    - Name of the miner

- *Transaction.java*

   A transaction can simply be expressed as the set of following elements.
   It contains the necessary fields for the complete representation of a transaction, including the following elements :
  
    - Identifier
    - Name of the sender
    - Name of the receiver
    - Amount

- *Miner.java*
  
  

  

- *Utility.java*

  This class is only used as a toolbox containing the definitions of two essential computation methods in the blockchain mechanism, namely the Merkle root computation and the Ajtai hash function

  

## Output

The execution of the Environment class simply 
Below is an example output of the project's execution. As we can observe, Miner N°4 has demonstrated a proof of work before its three other concurrents and has thus won 1 bitcoin as a reward.

![alt text](https://github.com/WatiBenj/Blockchain_Ajtai_Hash/blob/master/Output.PNG)

## Support

Benjamin Fischer - benjamin.fischer@etu.unige.ch

## Future Work

Implementation of the signing process to guarantee the authenticity of transactions and the integrity of blocks,
using a hash-based signature scheme (XMSS) to achieve a fully quantum-secure blockchain simulation.


