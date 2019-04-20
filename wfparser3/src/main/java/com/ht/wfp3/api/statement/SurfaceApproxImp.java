package com.ht.wfp3.api.statement;

abstract class SurfaceApproxImp extends StatementImp implements SurfaceApprox {
  private static final String KEYWORD = "stech";
  
  private final SurfaceApprox.Technique surfaceApproxmationTechnique;
  
  SurfaceApproxImp(SurfaceApprox.Technique surfaceApproximationTechnique) {
    super(KEYWORD);
    this.surfaceApproxmationTechnique = surfaceApproximationTechnique;
  }
  
  SurfaceApproxImp(SurfaceApprox surfaceApprox) {
    this(surfaceApprox.getSurfaceApproximationTechnique());
  }

  @Override
  public Technique getSurfaceApproximationTechnique() {
    return surfaceApproxmationTechnique;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((surfaceApproxmationTechnique == null) ? 0 : surfaceApproxmationTechnique.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    SurfaceApproxImp other = (SurfaceApproxImp) obj;
    if (surfaceApproxmationTechnique != other.surfaceApproxmationTechnique)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SurfaceApproxImp [surfaceApproxmationTechnique=" + surfaceApproxmationTechnique
        + ", super.toString()=" + super.toString() + "]";
  }
}
