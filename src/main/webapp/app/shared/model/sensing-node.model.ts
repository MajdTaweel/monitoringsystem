import { IMagnetometerReadings } from 'app/shared/model/magnetometer-readings.model';
import { IPollutionReadings } from 'app/shared/model/pollution-readings.model';
import { SensingNodeType } from 'app/shared/model/enumerations/sensing-node-type.model';
import { SensingNodeStatus } from 'app/shared/model/enumerations/sensing-node-status.model';

export interface ISensingNode {
  id?: number;
  snid?: string;
  sensingNodeType?: SensingNodeType;
  status?: SensingNodeStatus;
  battery?: number;
  userId?: number;
  magnetometerReadings?: IMagnetometerReadings[];
  pollutionReadings?: IPollutionReadings[];
}

export class SensingNode implements ISensingNode {
  constructor(
    public id?: number,
    public snid?: string,
    public sensingNodeType?: SensingNodeType,
    public status?: SensingNodeStatus,
    public battery?: number,
    public userId?: number,
    public magnetometerReadings?: IMagnetometerReadings[],
    public pollutionReadings?: IPollutionReadings[]
  ) {}
}
