package Model;
// Generated 08/06/2017 15:34:03 by Hibernate Tools 4.3.1



/**
 * AtendimentoDrgId generated by hbm2java
 */
public class AtendimentoDrgId  implements java.io.Serializable {


     private int idhospital;
     private short codigodrg;

    public AtendimentoDrgId() {
    }

    public AtendimentoDrgId(int idhospital, short codigodrg) {
       this.idhospital = idhospital;
       this.codigodrg = codigodrg;
    }
   
    public int getIdhospital() {
        return this.idhospital;
    }
    
    public void setIdhospital(int idhospital) {
        this.idhospital = idhospital;
    }
    public short getCodigodrg() {
        return this.codigodrg;
    }
    
    public void setCodigodrg(short codigodrg) {
        this.codigodrg = codigodrg;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AtendimentoDrgId) ) return false;
		 AtendimentoDrgId castOther = ( AtendimentoDrgId ) other; 
         
		 return (this.getIdhospital()==castOther.getIdhospital())
 && (this.getCodigodrg()==castOther.getCodigodrg());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdhospital();
         result = 37 * result + this.getCodigodrg();
         return result;
   }   


}


