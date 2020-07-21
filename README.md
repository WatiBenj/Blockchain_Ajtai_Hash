# Blockchain - Ajtai Hash Function

Blockchain Simulation using Lattice-Based Ajtai Hash Function

## Introduction

In this project, which is part of a Bachelor's thesis in Computer Science on the theme of *post-quantum cryptography*,
it is proposed to use a lattice-based hash function, better known as the *Ajtai hash function*,
instead of the standard SHA-256, in the implementation of a basic blockchain.

## Description

This blockchain simulation is composed of the following 5 Java classes :

- *Environment.java* (Main class)

  This class includes all the stages required to add a new block to the blockchain. More precisely, this procedure can be described by the following steps :
  
  1.  Definition of the Ajtai key (binary random matrix)
  2.  Instanciation of the first block (genesis)
  3.  Addition of the genesis block to the (empty) blockchain
  3.  Instanciation of 4 miners with their own copy of the Ajtai key and the initial blockchain
  4.  Instanciation of 8 transactions
  5.  Computation of the Merkle root from the set of transactions by each miner
  6.  Mining process :
      - Each miner attempts succesively to mine the next block by applying the Ajtai hash function on the concatenation of the hash of the previous bloc, the Merkle            root and the nonce
      - This step is repeated many times by randomly varying the nonce until the proof of work is verified (hash must begin with a certain number of 0)
      - The process ends immediately as soon as one of the miners obtains the desired hash value
  7.  Addition of the new mined block to the blockchain of each miner
  8.  Verification of the blockchain's validity by each miner
  9.  Attribution of 1 Bitcoin to the miner who mined the new block
  
- *Block.java*

  The necessary fields for the complete representation of a block include the following elements :
  
    - Hash of the previous block
    - Merkle root (computed from a set of transactions)
    - Nonce
    - Hash of the block (AjtaiHash(Previous Hash + Merkle Root + Nonce))
    - Timestamp (date + time)
    - Name of the miner

- *Transaction.java*

   A transaction can simply be expressed as the set of the next fields :
  
    - Identifier
    - Name of the sender
    - Name of the receiver
    - Amount

- *Miner.java*
  
  Eac

  

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


