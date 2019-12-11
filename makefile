SP=./src/
STP=./srctest/
CP=./classes/
CTP=./classestest/

CC=javac
PARAM=-cp $(CP) -d $(CP) -sourcepath $(SP) -s $(SP)

DASIAVENTURE=fr/insarouen/asi/prog/asiaventure
PASIAVENTURE=fr.insarouen.asi.prog.asiaventure
DELEMENTS=$(DASIAVENTURE)/elements
PELEMENTS=$(PASIAVENTURE).elements
DOBJETS=$(DELEMENTS)/objets
POBJETS=$(PELEMENTS).objets
DSTRUCTURE=$(DELEMENTS)/structure
PSTRUCTURE=$(PELEMENTS).structure
DVIVANTS=$(DELEMENTS)/vivants
PVIVANTS=$(PELEMENTS).vivants
DINTERFACES=$(DASIAVENTURE)/interfaces
PINTERFACES=$(PASIAVENTURE).interfaces
DSERRURERIE=$(DOBJETS)/serrurerie
PSERRURERIE=$(POBJETS).serrurerie


.PHONY : doc clean all tests  dir exectest

GK=/.gitkeep

all : clean compiler

dir :
	mkdir -p $(STP)$(DOBJETS)
	touch $(STP)$(DOBJETS)$(GK)
	mkdir -p $(STP)$(DSTRUCTURE)
	touch $(STP)$(DSTRUCTURE)$(GK)
	mkdir -p $(STP)$(DVIVANTS)
	touch $(STP)$(DVIVANTS)$(GK)
	mkdir -p $(STP)$(DINTERFACES)
	touch $(STP)$(DINTERFACES)$(GK)
	mkdir -p $(STP)$(DSERRURERIE)
	touch $(STP)$(DSERRURERIE)$(GK)
	mkdir -p $(CTP)$(DOBJETS)
	touch $(CTP)$(DOBJETS)$(GK)
	mkdir -p $(CTP)$(DSTRUCTURE)
	touch $(CTP)$(DSTRUCTURE)$(GK)
	mkdir -p $(CTP)$(DVIVANTS)
	touch $(CTP)$(DVIVANTS)$(GK)
	mkdir -p $(CTP)$(DINTERFACES)
	touch $(CTP)$(DINTERFACES)$(GK)
	mkdir -p $(CTP)$(DSERRURERIE)
	touch $(CTP)$(DSERRURERIE)$(GK)

clean :
	rm -rf ./classes
	rm -rf ./classestest
	rm -rf ./doc

main : clean
	mkdir -p ./classes
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/Main.java

compiler : clean
	mkdir -p ./classes

	$(CC) $(PARAM) $(SP)$(DSTRUCTURE)/ElementStructurel.java
	$(CC) $(PARAM) $(SP)$(DSTRUCTURE)/Piece.java
	$(CC) $(PARAM) $(SP)$(DVIVANTS)/Vivant.java
	$(CC) $(PARAM) $(SP)$(DOBJETS)/Objet.java
	$(CC) $(PARAM) $(SP)$(DOBJETS)/PiedDeBiche.java
	$(CC) $(PARAM) $(SP)$(DELEMENTS)/Entite.java
	$(CC) $(PARAM) $(SP)$(DELEMENTS)/Etat.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/EtatDuJeu.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/Monde.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ASIAventureException.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/EntiteDejaDansUnAutreMondeException.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/MondeException.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/NomDEntiteDejaUtiliseDansLeMondeException.java
	$(CC) $(PARAM) $(SP)$(DVIVANTS)/ObjetNonPossedeParLeVivantException.java
	$(CC) $(PARAM) $(SP)$(DVIVANTS)/VivantException.java
	$(CC) $(PARAM) $(SP)$(DSTRUCTURE)/ElementStructurelException.java
	$(CC) $(PARAM) $(SP)$(DSTRUCTURE)/ObjetAbsentDeLaPieceException.java
	$(CC) $(PARAM) $(SP)$(DSTRUCTURE)/PieceException.java
	$(CC) $(PARAM) $(SP)$(DOBJETS)/ObjetException.java
	$(CC) $(PARAM) $(SP)$(DOBJETS)/ObjetNonDeplacableException.java
	$(CC) $(PARAM) $(SP)$(DSERRURERIE)/Serrure.java
	$(CC) $(PARAM) $(SP)$(DSERRURERIE)/Clef.java
	$(CC) $(PARAM) $(SP)$(DOBJETS)/Coffre.java
	$(CC) $(PARAM) $(SP)$(DVIVANTS)/Monstre.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ConditionDeFin.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ConditionDeFinConjonctionConditionDeFin.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ConditionDeFinVivantDansPiece.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ConditionDeFinVivantDansPieceEtPossedeObjets.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ConditionDeFinVivantMort.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/ConditionDeFinVivantPossedeObjets.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/Simulateur.java
	$(CC) $(PARAM) $(SP)$(DASIAVENTURE)/Main.java







DOC=javadoc
UTF8=-encoding utf8 -docencoding utf8 -charset utf8
DOCPARAM=-cp $(CP) -sourcepath $(SP) -subpackages $(PASIAVENTURE) -d ./doc $(UTF8)

doc :
	mkdir -p ./doc
	$(DOC) $(DOCPARAM)



SEP=echo "\n\n\n"
EXEC=$(SEP);java
EXECPARAM=-cp $(CP)




JUNIT=/usr/share/java/junit4-4.12.jar
HEM=./lib/hamcrest-2.1.jar
TESTPARAM=-cp $(CTP):$(CP):$(JUNIT):$(HEM) -d $(CTP) -sourcepath $(STP) -s $(STP)

tests :
	rm -rf ./classestest
	mkdir -p ./classestest
	$(CC) $(TESTPARAM) $(STP)$(DASIAVENTURE)/TestMonde.java
	$(CC) $(TESTPARAM) $(STP)$(DELEMENTS)/TestEntite.java
	$(CC) $(TESTPARAM) $(STP)$(DVIVANTS)/TestVivant.java
	$(CC) $(TESTPARAM) $(STP)$(DVIVANTS)/TestMonstre.java
	$(CC) $(TESTPARAM) $(STP)$(DSTRUCTURE)/TestPiece.java
	$(CC) $(TESTPARAM) $(SP)$(DSERRURERIE)/TestSerrure.java
	$(CC) $(TESTPARAM) $(SP)$(DSERRURERIE)/TestClef.java



JUCORE=org.junit.runner.JUnitCore
TUPARAM=-cp $(CP):$(CTP):$(JUNIT):$(HEM) $(JUCORE)

exectest :
	$(EXEC) $(TUPARAM) $(PASIAVENTURE).TestMonde
	$(EXEC) $(TUPARAM) $(PELEMENTS).TestEntite
	$(EXEC) $(TUPARAM) $(PVIVANTS).TestVivant
	$(EXEC) $(TUPARAM) $(PVIVANTS).TestMonste
	$(EXEC) $(TUPARAM) $(PSTRUCTURE).TestPiece
	$(EXEC) $(TUPARAM) $(PSERRURERIE).TestSerrure
	$(EXEC) $(TUPARAM) $(PSERRURERIE).TestClef
